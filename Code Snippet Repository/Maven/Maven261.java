    public ClasspathContainer transform( MetadataGraph dirtyGraph, ArtifactScopeEnum scope, boolean resolve )
        throws MetadataGraphTransformationException
    {
        try
        {
            if ( dirtyGraph == null || dirtyGraph.isEmpty() )
            {
                return null;
            }

            MetadataGraph cleanGraph = conflictResolver.resolveConflicts( dirtyGraph, scope );

            if ( cleanGraph == null || cleanGraph.isEmpty() )
            {
                return null;
            }

            ClasspathContainer cpc = new ClasspathContainer( scope );
            if ( cleanGraph.isEmptyEdges() )
            {
                // single entry in the classpath, populated from itself
                ArtifactMetadata amd = cleanGraph.getEntry().getMd();
                cpc.add( amd );
            }
            else
            {
                ClasspathGraphVisitor v = new ClasspathGraphVisitor( cleanGraph, cpc );
                MetadataGraphVertex entry = cleanGraph.getEntry();
                // entry point
                v.visit( entry );
            }

            return cpc;
        }
        catch ( GraphConflictResolutionException e )
        {
            throw new MetadataGraphTransformationException( e );
        }
    }
