    @Inject
    public CoreExportsProvider( PlexusContainer container, @Nullable CoreExports exports )
    {
        if ( exports == null )
        {
            this.exports = new CoreExports( CoreExtensionEntry.discoverFrom( container.getContainerRealm() ) );
        }
        else
        {
            this.exports = exports;
        }
    }
