    public MetadataGraph getGraph( ArtifactScopeEnum scope )
        throws MetadataResolutionException, GraphConflictResolutionException
    {
        if ( treeRoot == null )
        {
            return null;
        }

        if ( conflictResolver == null )
        {
            return null;
        }

        return conflictResolver.resolveConflicts( getGraph(), scope );
    }
