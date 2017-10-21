    public DefaultProfileActivationContext setProjectProperties( Properties projectProperties )
    {
        if ( projectProperties != null )
        {

            this.projectProperties = Collections.unmodifiableMap( toMap( projectProperties ) );
        }
        else
        {
            this.projectProperties = Collections.emptyMap();
        }

        return this;
    }
