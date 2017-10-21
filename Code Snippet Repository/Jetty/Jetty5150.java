    public static Logger getLogger(String name)
    {
        initialized();

        if(name==null)
            return LOG;

        Logger logger = __loggers.get(name);
        if(logger==null)
            logger = LOG.getLogger(name);

        return logger;
    }
