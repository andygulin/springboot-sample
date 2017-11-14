# Spring Kafka Sample

### 首先安装JDK、zookeeper
### 安装kafka
	cd /data
	tar zxvf kafka_2.11-0.10.1.1.tgz && mvkafka_2.11-0.10.1.1 kafka
	vi kafka/config/server.properties
	添加
	advertised.host.name=IP地址
	设置
	zookeeper.connect=IP地址
	
	启动zookeeper
	/data/zookeeper/bin/zkServer.sh start
	启动Kafka
	/data/kafka/bin/kafka-server-start.sh -daemon /data/kafka/config/server.properties
	