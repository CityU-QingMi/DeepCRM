    public MetadataGraph( MetadataTreeNode tree, boolean versionedVertices, boolean scopedVertices )
        throws MetadataResolutionException
    {
        if ( tree == null )
        {
            throw new MetadataResolutionException( "tree is null" );
        }

        setVersionedVertices( versionedVertices );
        setScopedVertices( scopedVertices );

        this.versionedVertices = scopedVertices || versionedVertices;
        this.scopedVertices = scopedVertices;

        int count = countNodes( tree );

        init( count, count + ( count / 2 ) );

        processTreeNodes( null, tree, 0, 0 );
    }
