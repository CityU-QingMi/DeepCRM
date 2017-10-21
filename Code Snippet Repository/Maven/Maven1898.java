    @Override
    protected String renderLevel( int level )
    {
        switch ( level )
        {
            case LOG_LEVEL_TRACE:
                return buffer().debug( "TRACE" ).toString();
            case LOG_LEVEL_DEBUG:
                return buffer().debug( "DEBUG" ).toString();
            case LOG_LEVEL_INFO:
                return buffer().info( "INFO" ).toString();
            case LOG_LEVEL_WARN:
                return buffer().warning( "WARNING" ).toString();
            case LOG_LEVEL_ERROR:
            default:
                return buffer().error( "ERROR" ).toString();
        }
    }
