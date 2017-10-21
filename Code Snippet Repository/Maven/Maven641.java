    @Deprecated
    public Properties getExecutionProperties()
    {
        if ( executionProperties == null )
        {
            executionProperties = new Properties();
            executionProperties.putAll( request.getSystemProperties() );
            executionProperties.putAll( request.getUserProperties() );
        }

        return executionProperties;
    }
