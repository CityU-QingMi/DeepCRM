    private ArtifactFilter getArtifactFilter( MojoDescriptor mojoDescriptor )
    {
        String scopeToResolve = mojoDescriptor.getDependencyResolutionRequired();
        String scopeToCollect = mojoDescriptor.getDependencyCollectionRequired();

        List<String> scopes = new ArrayList<>( 2 );
        if ( StringUtils.isNotEmpty( scopeToCollect ) )
        {
            scopes.add( scopeToCollect );
        }
        if ( StringUtils.isNotEmpty( scopeToResolve ) )
        {
            scopes.add( scopeToResolve );
        }

        if ( scopes.isEmpty() )
        {
            return null;
        }
        else
        {
            return new CumulativeScopeArtifactFilter( scopes );
        }
    }
