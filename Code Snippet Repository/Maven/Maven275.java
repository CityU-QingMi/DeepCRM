    public MetadataGraphVertex findVertex( ArtifactMetadata md )
    {
        if ( md == null || vertices == null || vertices.size() < 1 )
        {
            return null;
        }

        MetadataGraphVertex v = new MetadataGraphVertex( md );
        v.setCompareVersion( versionedVertices );
        v.setCompareScope( scopedVertices );

        for ( MetadataGraphVertex gv : vertices )
        {
            if ( gv.equals( v ) )
            {
                return gv;
            }
        }

        return null;
    }
