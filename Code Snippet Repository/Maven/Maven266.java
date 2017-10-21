    public MetadataGraph( MetadataGraphVertex entry )
        throws MetadataResolutionException
    {
        checkVertex( entry );
        checkVertices( 1 );

        entry.setCompareVersion( versionedVertices );
        entry.setCompareScope( scopedVertices );

        vertices.add( entry );
        this.entry = entry;
    }
