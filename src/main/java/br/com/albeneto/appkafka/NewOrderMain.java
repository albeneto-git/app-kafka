package br.com.albeneto.appkafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class NewOrderMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		/*
		 * Este código consiste em produzir uma mensagem no tópico: ECOMMERCE_NEW_ORDER
		 * Como estamos testando no wsl devemos obter o ip do wsl com o comando: wsl hostname -I
		 * É necessário configurar onde o kafka esta rodando bem como as classes de serialização e deserialização da mensagem
		 */
		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(getProperties());
		String value = "id_order_999,id_usu_aneto,123";
		ProducerRecord<String, String> producerRecord = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", value, value);
		Callback callback = (data, ex) -> {
	        if (ex != null) {
	            System.out.println("Ocorreu um erro ao mandar a mensagem: " + ex.getMessage());
	            return;
	        }
	        System.out.println("sucesso enviando " + data.topic() + ":::partition " + data.partition() + "/ offset " + data.offset() + "/ timestamp " + data.timestamp());
	    };
	    String email = "Thans you for your order! We are processing your order!";
	    ProducerRecord<String, String> emailRecord = new ProducerRecord<>("ECOMMERCE_RECORD_SEND_EMAIL", email, email);
		kafkaProducer.send(producerRecord, callback).get();
		kafkaProducer.send(emailRecord, callback).get();
	}

	private static Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.21.36.210:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}

}
