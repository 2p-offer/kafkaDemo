import java.util.ArrayList;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Created by 2P on 18-11-13.
 */
public class producer {
    public static void main(String[] args) throws Exception {


        String[] arg = {"operatorlog"};

        String topicName = arg[0].toString();

        Properties props = new Properties();
        props.put("bootstrap.servers", "10.10.220.91:9092 ");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer",
                "org.apache.kafka.common.serialization.ByteArraySerializer");

        Producer<String, byte[]> producer = new KafkaProducer(props);
        for(int i=3;i<5;i++){
            JSONObject js=new JSONObject();
            js.put("id",i+":ids"+i);
            js.put("platform","platform");
            js.put("business","business");
            js.put("owner","owner");
            js.put("time","1547706558111");
            js.put("costNano","0");
            byte[] bytes = js.toJSONString().getBytes();
            System.out.println(new String(bytes));
            producer.send(new ProducerRecord(topicName,bytes));

            System.out.println(i+"  Message sent successfully");
        }

        producer.close();


//        String s="11111:123";
//        String substring = s.substring(0, s.indexOf(":") + 1);
//        System.out.println(substring);
//

    }
}