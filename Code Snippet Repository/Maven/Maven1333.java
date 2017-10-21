    @Override
    public void setRootLoggerLevel( Level level )
    {
        String value;
        switch ( level )
        {
            case DEBUG:
                value = "debug";
                break;

            case INFO:
                value = "info";
                break;

            default:
                value = "error";
                break;
        }
        System.setProperty( "org.slf4j.simpleLogger.defaultLogLevel", value );
    }
