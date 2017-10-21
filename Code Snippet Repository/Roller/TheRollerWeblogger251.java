    public static void main(String[] args) throws Exception {
        try {
            ResetHitCountsTask task = new ResetHitCountsTask();
            task.init();
            task.run();
            System.exit(0);
        } catch (WebloggerException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
