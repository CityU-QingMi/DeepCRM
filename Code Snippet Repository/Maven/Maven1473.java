    @SuppressWarnings( "" )
    public DefaultProfileActivationContext setUserProperties( Properties userProperties )
    {
        if ( userProperties != null )
        {
            this.userProperties = Collections.unmodifiableMap( (Map) userProperties );
        }
        else
        {
            this.userProperties = Collections.emptyMap();
        }

        return this;
    }
