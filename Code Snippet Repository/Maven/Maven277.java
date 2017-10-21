    private void init( int nVertices, int nEdges )
    {
        int nV = nVertices;
        if ( nVertices < 1 )
        {
            nV = 1;
        }

        checkVertices( nV );

        int nE = nVertices;
        if ( nEdges <= nV )
        {
            nE = 2 * nE;
        }

        checkEdges( nE );
    }
