    public void usage(boolean exit)
    {
        StartLog.endStartLog();
        if(!printTextResource("org/eclipse/jetty/start/usage.txt"))
        {
            StartLog.warn("detailed usage resource unavailable");
        }
        if (exit)
        {
            System.exit(EXIT_USAGE);
        }
    }
