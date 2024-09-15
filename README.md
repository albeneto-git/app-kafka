# Aplicação Kafka

Objetivo apresentar os principais conceitos e componentes de uma aplicação de mensageria usando KAFKA.


### 📋 Pré-requisitos

Dependências:

```
	<!-- https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients -->
	<dependency>
	    <groupId>org.apache.kafka</groupId>
	    <artifactId>kafka-clients</artifactId>
	    <version>3.7.0</version>
	</dependency>
	<!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-simple -->
	<dependency>
	    <groupId>org.slf4j</groupId>
	    <artifactId>slf4j-simple</artifactId>
	    <version>2.0.13</version>
	    <scope>test</scope>
	</dependency>
```

### 🔧 Instalação

Instale o kafka, acessando o site: 
* [Kafka](https://kafka.apache.org/downloads) - Para download

```
Descompacte no diretório de sua escolha.
Será descompactado vários diretórios o que nos enteressa é o /bin
```

## ⚙️ Comandos úteis para executar o kafka no terminal

```
bin/zookeeper-server-start.sh config/zookeeper.properties		(Para Start do Zookeeper)
bin/kafka-server-start.sh config/server.properties				(Em seguida start do kafka)
bin/kafka-topics.sh --list --bootstrap-server localhost:9092 (Para listar os tópicos)
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic ECOMMERCE_NEW_ORDER  	(Para criar um tópico)
bin/kafka-console-producer.sh -- broker-list localhost:9092 ECOMMERCE_NEW_ORDER 																(Para p roduzir uma mensagem no tópico)
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ECOMMERCE_NEW_ORDER													(Consumir as mensagens)
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ECOMMERCE_NEW_ORDER --from-beginning								(Consumir a mensagem desde o inicio das mensagens)

```

