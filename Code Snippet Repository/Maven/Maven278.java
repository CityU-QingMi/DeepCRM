    private void checkEdges()
    {
        int count = DEFAULT_EDGES;

        if ( vertices != null )
        {
            count = vertices.size() + vertices.size() / 2;
        }

        checkEdges( count );
    }
