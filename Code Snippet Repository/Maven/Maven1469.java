    public DefaultProfileActivationContext setActiveProfileIds( List<String> activeProfileIds )
    {
        if ( activeProfileIds != null )
        {
            this.activeProfileIds = Collections.unmodifiableList( activeProfileIds );
        }
        else
        {
            this.activeProfileIds = Collections.emptyList();
        }

        return this;
    }
