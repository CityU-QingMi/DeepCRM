    public MetadataGraph getGraph( MetadataResolutionRequestTypeEnum requestType )
        throws MetadataResolutionException, GraphConflictResolutionException
    {
        if ( requestType == null )
        {
            return null;
        }

        if ( treeRoot == null )
        {
            return null;
        }

        if ( conflictResolver == null )
        {
            return null;
        }

        if ( requestType.equals( MetadataResolutionRequestTypeEnum.classpathCompile ) )
        {
            return conflictResolver.resolveConflicts( getGraph(), ArtifactScopeEnum.compile );
        }
        else if ( requestType.equals( MetadataResolutionRequestTypeEnum.classpathRuntime ) )
        {
            return conflictResolver.resolveConflicts( getGraph(), ArtifactScopeEnum.runtime );
        }
        else if ( requestType.equals( MetadataResolutionRequestTypeEnum.classpathTest ) )
        {
            return conflictResolver.resolveConflicts( getGraph(), ArtifactScopeEnum.test );
        }
        else if ( requestType.equals( MetadataResolutionRequestTypeEnum.graph ) )
        {
            return getGraph();
        }
        else if ( requestType.equals( MetadataResolutionRequestTypeEnum.versionedGraph ) )
        {
            return new MetadataGraph( getTree(), true, false );
        }
        else if ( requestType.equals( MetadataResolutionRequestTypeEnum.scopedGraph ) )
        {
            return new MetadataGraph( getTree(), true, true );
        }
        return null;
    }
