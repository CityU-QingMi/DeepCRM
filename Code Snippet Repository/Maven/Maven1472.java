    public DefaultProfileActivationContext setSystemProperties( Map<String, String> systemProperties )
    {
        if ( systemProperties != null )
        {
            this.systemProperties = Collections.unmodifiableMap( systemProperties );
        }
        else
        {
            this.systemProperties = Collections.emptyMap();
        }

        return this;
    }
