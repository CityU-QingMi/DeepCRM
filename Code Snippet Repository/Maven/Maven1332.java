    @Override
    public void setRootLoggerLevel( Level level )
    {
        ch.qos.logback.classic.Level value;
        switch ( level )
        {
            case DEBUG:
                value = ch.qos.logback.classic.Level.DEBUG;
                break;

            case INFO:
                value = ch.qos.logback.classic.Level.INFO;
                break;

            default:
                value = ch.qos.logback.classic.Level.ERROR;
                break;
        }
        ( (ch.qos.logback.classic.Logger) LoggerFactory.getLogger( Logger.ROOT_LOGGER_NAME ) ).setLevel( value );
    }
