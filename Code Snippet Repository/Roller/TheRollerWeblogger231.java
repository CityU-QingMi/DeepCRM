    public static void main(String[] args) throws Exception {
        try {
            PingQueueTask task = new PingQueueTask();
            task.init();
            task.run();
            System.exit(0);
        } catch (WebloggerException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
