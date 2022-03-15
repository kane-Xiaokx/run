package cn.koch.rocket;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.common.protocol.route.TopicRouteData;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.tools.admin.DefaultMQAdminExt;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SimpleTest {


    @Test
    public void producer() throws Exception {

        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("xxoo");
        defaultMQProducer.setNamesrvAddr("localhost:9876");
        defaultMQProducer.start();
        Message message = new Message();
        message.setBody("hello consumer".getBytes(StandardCharsets.UTF_8));
        message.setTopic("xx");
        message.setWaitStoreMsgOK(true);
        //message.setDelayTimeLevel(); 消息延迟级别
        CountDownLatch downLatch = new CountDownLatch(1);
        defaultMQProducer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("send success!!");
                System.out.println("result:"+sendResult);
                downLatch.countDown();
            }

            @Override
            public void onException(Throwable e) {
                System.out.println("send fail ");
                e.printStackTrace();
                downLatch.countDown();
            }
        });
        downLatch.await();
    }


    @Test
    public void transactionProducer() throws Exception{
        TransactionMQProducer transactionMQProducer= new TransactionMQProducer("xxoo");
        transactionMQProducer.setNamesrvAddr("localhost:9876");
        transactionMQProducer.start();
        CountDownLatch count = new CountDownLatch(1);
        transactionMQProducer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                System.out.println("inner:"+msg);
                System.out.println("arg"+arg);
                count.countDown();
                return LocalTransactionState.COMMIT_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.out.println("fail:"+msg);
                count.countDown();
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        });
        SendResult xx = transactionMQProducer.sendMessageInTransaction(new Message("xx", "transaction5".getBytes(StandardCharsets.UTF_8)),"这个是arg");
        String transactionId = xx.getTransactionId();
        System.out.println("out："+transactionId);
        SendStatus sendStatus = xx.getSendStatus();
        count.await();
    }


    @Test
    public void consumerPush() throws Exception {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("ooxx1");
        defaultMQPushConsumer.setNamesrvAddr("localhost:9876");
        defaultMQPushConsumer.subscribe("xx","*");
        defaultMQPushConsumer.setMaxReconsumeTimes(2); //消息失败,最大的重试次数

        defaultMQPushConsumer.setConsumeMessageBatchMaxSize(1); //默认每次拉取32条消息,拉取完后只把一条消息交给消费逻辑处理
        //defaultMQPushConsumer.setPullBatchSize(); //每次最多拉取多少条消息
        defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);
        CountDownLatch downLatch = new CountDownLatch(1);
        defaultMQPushConsumer.registerMessageListener(new MessageListenerOrderly() {

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("msg:"+new String(msg.getBody()));
                    System.out.println(msg);
                }
              //  downLatch.countDown();
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        defaultMQPushConsumer.start();
        downLatch.await();
    }

    @Test
    public void consumerPull() throws MQClientException, IOException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_ox");
        consumer.setNamesrvAddr("localhost:9876");

        consumer.subscribe("xx","*");


        consumer.registerMessageListener(new MessageListenerConcurrently() {
            //小批量，小集合
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                msgs.forEach(messageExt -> {
//                    System.out.println(messageExt);
                    byte[] body = messageExt.getBody();
                    String msg = new String(body);
                    System.out.println(msg);
                });

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();

        System.in.read();

    }

    @Test
    public void admin() throws RemotingException, InterruptedException, MQClientException {
        DefaultMQAdminExt defaultMQAdminExt = new DefaultMQAdminExt();
        defaultMQAdminExt.setNamesrvAddr("localhost:9876");
        defaultMQAdminExt.start();
        /*TopicList topicList = defaultMQAdminExt.fetchAllTopicList();
        Set<String> topicList1 = topicList.getTopicList();
        for (String s : topicList1) {
            System.out.println(s);
        }*/
        TopicRouteData xx = defaultMQAdminExt.examineTopicRouteInfo("xx");
        System.out.println(xx);
    }

    public static void main(String[] args) throws IOException {
        int read = System.in.read();
        System.out.println(read);
    }


}
