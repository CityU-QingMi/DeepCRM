    public ClasspathContainer getClasspath( ArtifactScopeEnum scope )
        throws MetadataGraphTransformationException, MetadataResolutionException
    {
        if ( classpathTransformation == null )
        {
            return null;
        }

        MetadataGraph dirtyGraph = getGraph();
        if ( dirtyGraph == null )
        {
            return null;
        }

        return classpathTransformation.transform( dirtyGraph, scope, false );
    }
