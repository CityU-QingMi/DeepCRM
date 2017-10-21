    @Parameterized.Parameters(name = "")
    public static Object[] data() {
        return new String[]{
            // default async config uses array blocking queue
            "log4j-asynch.xml",
            // override default blocking queue implementations
            "BlockingQueueFactory-ArrayBlockingQueue.xml",
            "BlockingQueueFactory-DisruptorBlockingQueue.xml",
            "BlockingQueueFactory-JCToolsBlockingQueue.xml",
            "BlockingQueueFactory-LinkedTransferQueue.xml"
        };
    }
