    @SuppressWarnings( "" )
    public DefaultProfileActivationContext setSystemProperties( Properties systemProperties )
    {
        if ( systemProperties != null )
        {
            this.systemProperties = Collections.unmodifiableMap( (Map) systemProperties );
        }
        else
        {
            this.systemProperties = Collections.emptyMap();
        }

        return this;
    }
