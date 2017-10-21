    public DefaultProfileActivationContext setInactiveProfileIds( List<String> inactiveProfileIds )
    {
        if ( inactiveProfileIds != null )
        {
            this.inactiveProfileIds = Collections.unmodifiableList( inactiveProfileIds );
        }
        else
        {
            this.inactiveProfileIds = Collections.emptyList();
        }

        return this;
    }
