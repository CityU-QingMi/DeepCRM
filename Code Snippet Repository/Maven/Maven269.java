    public MetadataGraph removeVertex( MetadataGraphVertex v )
    {
        if ( vertices != null && v != null )
        {
            vertices.remove( v );
        }

        if ( incidentEdges != null )
        {
            incidentEdges.remove( v );
        }

        if ( excidentEdges != null )
        {
            excidentEdges.remove( v );
        }

        return this;

    }
