    @Override
    public final Logger getLogger(String name)
    {
        if (isBlank(name))
            return this;

        final String basename = getName();
        final String fullname = (isBlank(basename) || Log.getRootLogger()==this)?name:(basename + "." + name);
        
        Logger logger = Log.getLoggers().get(fullname);
        if (logger == null)
        {
            Logger newlog = newLogger(fullname);
            
            logger = Log.getMutableLoggers().putIfAbsent(fullname,newlog);
            if (logger == null)
                logger=newlog;
        }

        return logger;
    }
