    @Override
    public MavenExecutionRequest setSystemProperties( Properties properties )
    {
        if ( properties != null )
        {
            this.systemProperties = SystemProperties.copyProperties( properties );
        }
        else
        {
            this.systemProperties = null;
        }

        return this;
    }
