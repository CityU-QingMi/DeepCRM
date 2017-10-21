    private MetadataGraph findLinkedSubgraph( MetadataGraph g )
    {
        if ( g.getVertices().size() == 1 )
        {
            return g;
        }

        List<MetadataGraphVertex> visited = new ArrayList<>( g.getVertices().size() );
        visit( g.getEntry(), visited, g );

        List<MetadataGraphVertex> dropList = new ArrayList<>( g.getVertices().size() );

        // collect drop list
        for ( MetadataGraphVertex v : g.getVertices() )
        {
            if ( !visited.contains( v ) )
            {
                dropList.add( v );
            }
        }

        if ( dropList.size() < 1 )
        {
            return g;
        }

        // now - drop vertices
        TreeSet<MetadataGraphVertex> vertices = g.getVertices();
        for ( MetadataGraphVertex v : dropList )
        {
            vertices.remove( v );
        }

        return g;
    }
