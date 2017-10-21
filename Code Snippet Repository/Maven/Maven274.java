    private void processTreeNodes( MetadataGraphVertex parentVertex, MetadataTreeNode node, int depth, int pomOrder )
        throws MetadataResolutionException
    {
        if ( node == null )
        {
            return;
        }

        MetadataGraphVertex vertex = new MetadataGraphVertex( node.md, versionedVertices, scopedVertices );
        if ( !vertices.contains( vertex ) )
        {
            vertices.add( vertex );
        }

        if ( parentVertex != null ) // then create the edge
        {
            ArtifactMetadata md = node.getMd();
            MetadataGraphEdge e =
                new MetadataGraphEdge( md.version, md.resolved, md.artifactScope, md.artifactUri, depth, pomOrder );
            addEdge( parentVertex, vertex, e );
        }
        else
        {
            entry = vertex;
        }

        MetadataTreeNode[] kids = node.getChildren();
        if ( kids == null || kids.length < 1 )
        {
            return;
        }

        for ( int i = 0; i < kids.length; i++ )
        {
            MetadataTreeNode n = kids[i];
            processTreeNodes( vertex, n, depth + 1, i );
        }
    }
