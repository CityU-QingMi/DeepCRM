    @Override
    public DefaultSettingsBuildingRequest setSystemProperties( Properties systemProperties )
    {
        if ( systemProperties != null )
        {
            this.systemProperties = new Properties();
            synchronized ( systemProperties )
            { // avoid concurrentmodification if someone else sets/removes an unrelated system property
                this.systemProperties.putAll( systemProperties );
            }
        }
        else
        {
            this.systemProperties = null;
        }

        return this;
    }
