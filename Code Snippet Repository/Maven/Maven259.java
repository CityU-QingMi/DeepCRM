    public MetadataTreeNode getClasspathAsTree()
        throws MetadataResolutionException
    {
        if ( classpath == null || classpath.size() < 1 )
        {
            return null;
        }

        MetadataTreeNode tree = null;
        MetadataTreeNode parent = null;

        for ( ArtifactMetadata md : classpath )
        {
            MetadataTreeNode node = new MetadataTreeNode( md, parent, md.isResolved(), md.getArtifactScope() );
            if ( tree == null )
            {
                tree = node;
            }

            if ( parent != null )
            {
                parent.setNChildren( 1 );
                parent.addChild( 0, node );
            }

            parent = node;

        }
        return tree;
    }
