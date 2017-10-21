    public CumulativeScopeArtifactFilter( CumulativeScopeArtifactFilter... filters )
    {
        this.scopes = new HashSet<>();

        if ( filters != null )
        {
            for ( CumulativeScopeArtifactFilter filter : filters )
            {
                addScopes( filter.getScopes() );
            }
        }
    }
