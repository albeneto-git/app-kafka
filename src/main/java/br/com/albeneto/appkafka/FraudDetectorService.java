package br.com.albeneto.appkafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class FraudDetectorService {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(getProperties());
		consumer.subscribe(Collections.singletonList("ECOMMERCE_NEW_ORDER"));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
			if(!records.isEmpty()) {
				System.out.println("Encontrei " + records.count() + " registros");
				for(ConsumerRecord<String, String> record : records) {
					System.out.println("******************************************************");
					System.out.println(" ***** Processing new order, checking for fraud!");
					System.out.println(" ***** Registro lido: Key: " + record.key() + " valor: " + record.value() + " OffSet: " + record.offset() + " Partition: " + record.partition());
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// Ignore
						e.printStackTrace();
					}
					System.out.println("Order processed!");
				}
			}
		}
	}

	private static Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.18.58.163:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, FraudDetectorService.class.getSimpleName());
		return properties;
	}

}
