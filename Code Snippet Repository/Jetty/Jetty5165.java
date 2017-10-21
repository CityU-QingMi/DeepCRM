    public static int getLoggingLevel(Properties props,String name)
    {
        int level = lookupLoggingLevel(props,name);
        if (level==LEVEL_DEFAULT)
        {
            level = lookupLoggingLevel(props,"log");
            if (level==LEVEL_DEFAULT)
                level=LEVEL_INFO;
        }
        return level;
    }
