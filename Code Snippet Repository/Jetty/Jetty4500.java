    static void usageExit(Throwable t, int exit, boolean test)
    {
        if (t != null)
        {
            t.printStackTrace(System.err);
        }
        System.err.println();
        System.err.println("Usage: java -jar $JETTY_HOME/start.jar [options] [properties] [configs]");
        System.err.println("       java -jar $JETTY_HOME/start.jar --help  # for more information");
        
        if (test)
            System.err.println("EXIT: "+exit);
        else
            System.exit(exit);
    }
