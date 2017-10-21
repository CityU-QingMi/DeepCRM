    public DependencySelector deriveChildSelector( DependencyCollectionContext context )
    {
        if ( coreArtifact || !isLegacyCoreArtifact( context.getDependency().getArtifact() ) )
        {
            return this;
        }
        else
        {
            return new WagonExcluder( true );
        }
    }
