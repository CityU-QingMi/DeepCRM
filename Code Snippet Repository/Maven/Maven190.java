    public MetadataGraphNode findNode( MavenArtifactMetadata md )
    {
        for ( MetadataGraphNode mgn : nodes )
        {
            if ( mgn.metadata.equals( md ) )
            {
                return mgn;
            }
        }

        MetadataGraphNode node = new MetadataGraphNode( md );
        addNode( node );
        return node;
    }
