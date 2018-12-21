import highConsumer.JavaKafkaConsumerHighAPI;

/**
 * Created by 2P on 18-11-13.
 */
public class highConsumerTest {
    public static void main(String[] args) {
        String zookeeper = "10.10.12.104:2181";
        String groupId = "group1";
        String topic = "Hello-Kafka";
        int threads = 1;

        JavaKafkaConsumerHighAPI example = new JavaKafkaConsumerHighAPI(topic, threads, zookeeper, groupId);
        new Thread(example).start();

        // 执行10秒后结束
        int sleepMillis = 600000;
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭
        example.shutdown();
    }
}
