    private void checkEdges( int nEdges )
    {
        if ( incidentEdges == null )
        {
            incidentEdges = new HashMap<>( nEdges );
        }
        if ( excidentEdges == null )
        {
            excidentEdges = new HashMap<>( nEdges );
        }
    }
