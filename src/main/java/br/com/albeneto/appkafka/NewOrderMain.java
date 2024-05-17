package br.com.albeneto.appkafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class NewOrderMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		KafkaProducer kafkaProducer = new KafkaProducer<String, String>(getProperties());
		String value = "id_order_123,id_usu_aneto,900";
		ProducerRecord<String, String> producerRecord = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", value, value);
		kafkaProducer.send(producerRecord, (data, ex) -> {
	        if (ex != null) {
	            System.out.println("Ocorreu um erro ao mandar a mensagem: " + ex.getMessage());
	            return;
	        }
	        System.out.println("sucesso enviando " + data.topic() + ":::partition " + data.partition() + "/ offset " + data.offset() + "/ timestamp " + data.timestamp());
	    }).get();
	}

	private static Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.18.58.163:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}

}
