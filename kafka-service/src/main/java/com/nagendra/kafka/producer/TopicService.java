package com.nagendra.kafka.producer;

import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer;
import kafka.utils.ZKStringSerializer$;
import org.I0Itec.zkclient.ZkClient;

import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by Nagendra on 12/8/15.
 */
public class TopicService {

    public static  void main(String args[]) {
        addTopic("test");
    }

    /**
     * Create a topic for <param>topicName</param>
     * @param topicName
     * @return
     */
    public static boolean addTopic(String topicName) {

        boolean isCreated = false;
        ZkClient zkClient = getZkClient();

        try {
            int numPartitions = 2;
            int replicationFactor = 1;
            Properties topicConfig = new Properties();
            AdminUtils.createTopic(zkClient, topicName, numPartitions, replicationFactor, topicConfig);
            isCreated = true;
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
        return isCreated;
    }

    /**
     * Delete a topic for <param>topicName</param>
     * @param topicName
     * @return
     */
    public static boolean deleteTopic(String topicName) {
        ZkClient zkClient = getZkClient();
        boolean isDeleted = false;
        try {
            int numPartitions = 2;
            int replicationFactor = 1;
            Properties topicConfig = new Properties();
            AdminUtils.deleteTopic(zkClient, topicName);
            isDeleted = true;
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
        return isDeleted;
    }

    private static ZkClient getZkClient() {
        int sessionTimeoutMs = 10000;
        int connectionTimeoutMs = 10000;
        return new ZkClient("localhost:2181", sessionTimeoutMs, connectionTimeoutMs,
                ZKStringSerializer$.MODULE$);
    }
}
