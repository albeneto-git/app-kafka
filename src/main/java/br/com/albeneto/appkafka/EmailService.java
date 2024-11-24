package br.com.albeneto.appkafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class EmailService {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(getProperties());
		consumer.subscribe(Collections.singletonList("ECOMMERCE_RECORD_SEND_EMAIL"));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
			if(!records.isEmpty()) {
				System.out.println("Encontrei " + records.count() + " registros");
				for(ConsumerRecord<String, String> record : records) {
					System.out.println("******************************************************");
					System.out.println(" ***** Send email! " + record.topic());
					System.out.println(" ***** Registro lido: Key: " + record.key() + " valor: " + record.value() + " OffSet: " + record.offset() + " Partition: " + record.partition());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// Ignore
						e.printStackTrace();
					}
					System.out.println("Email sent!");
				}
			}
		}
	}

	private static Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.21.36.210:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, EmailService.class.getSimpleName());
		return properties;
	}
}
