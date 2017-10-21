    public DefaultProfileManager( PlexusContainer container, Properties props )
    {
        try
        {
            this.profileSelector = container.lookup( ProfileSelector.class );
            this.logger = ( (MutablePlexusContainer) container ).getLogger();
        }
        catch ( ComponentLookupException e )
        {
            throw new IllegalStateException( e );
        }
        this.requestProperties = props;
    }
