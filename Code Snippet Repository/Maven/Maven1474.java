    public DefaultProfileActivationContext setUserProperties( Map<String, String> userProperties )
    {
        if ( userProperties != null )
        {
            this.userProperties = Collections.unmodifiableMap( userProperties );
        }
        else
        {
            this.userProperties = Collections.emptyMap();
        }

        return this;
    }
