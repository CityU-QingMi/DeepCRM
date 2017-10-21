    public MetadataGraphVertex addVertex( ArtifactMetadata md )
    {
        if ( md == null )
        {
            return null;
        }

        checkVertices();

        MetadataGraphVertex v = findVertex( md );
        if ( v != null )
        {
            return v;
        }

        v = new MetadataGraphVertex( md );

        v.setCompareVersion( versionedVertices );
        v.setCompareScope( scopedVertices );

        vertices.add( v );
        return v;
    }
