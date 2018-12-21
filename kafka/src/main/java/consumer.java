
import java.util.Properties;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;


/**
 * Created by 2P on 18-11-13.
 */
public class consumer {
    public static void main(String[] args) throws Exception {

        String[] arg = {"Mutilbroker", "Hello-Kafka"};
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer
                <String, String>(props);

        consumer.subscribe(Arrays.asList(arg));

        int i = 0;

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {

                // print the offset,key and value for the consumer records.
//                System.out.printf("offset = %d, key = %s, value = %s\n",
//                        record.offset(), record.key(), record.value());
                    System.out.println("offset:"+record.offset() +"key:"+record.key() +"value"+ record.value());
            }
        }
    }
}
