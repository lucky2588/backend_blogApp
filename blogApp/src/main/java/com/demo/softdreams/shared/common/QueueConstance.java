package com.demo.softdreams.shared.common;

import java.util.Arrays;
import java.util.List;

public class QueueConstance {


    private QueueConstance() {
    }


    public static class Common {
        private Common() {
        }

        public static final String RPC_QUEUE1 = "queue_1";
        public static final String RPC_QUEUE2 = "queue_2";
        public static final String RPC_EXCHANGE = "rpc_exchange";

        public static final String QUEUE = "mess.queue";

        public static final String EXCHANGE = "mess.exchange";
        public static final String ROUTINGKEY = "mess.routingkey";
        public static final String REPLY_QUEUE = "mess.reply.queue";
        public static final String REPLY_ROUTING_KEY = "mess.reply.routingkey";

    }



    public static class EmailService {
        private EmailService() {
        }



    }

    public class JobQueue {
        public static final String QUEUE_DEV = "rabbit-queue-dev";
        public static final List<String> queueNameList = Arrays.asList(QUEUE_DEV);
    }




}
