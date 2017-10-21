    public static void main(String[] args) throws Exception {

        //    //Configure Log4J
        //    org.apache.log4j.PropertyConfigurator.configure(
        //      System.getProperty("log4jConfigFile", "log4j.properties"));

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new java.rmi.RMISecurityManager());
        }

        try {
            QuartzServer server = new QuartzServer();
            if (args.length == 0) {
                server.serve(
                    new org.quartz.impl.StdSchedulerFactory(), false);
            } else if (args.length == 1 && args[0].equalsIgnoreCase("console")) {
                server.serve(new org.quartz.impl.StdSchedulerFactory(), true);
            } else {
                System.err.println("\nUsage: QuartzServer [console]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
