    public MetadataTreeNode getClasspathTree( ArtifactScopeEnum scope )
        throws MetadataGraphTransformationException, MetadataResolutionException
    {
        ClasspathContainer cpc = getClasspath( scope );
        if ( cpc == null )
        {
            return null;
        }

        return cpc.getClasspathAsTree();
    }
