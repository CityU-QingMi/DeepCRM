    private void visit( MetadataGraphVertex from, List<MetadataGraphVertex> visited, MetadataGraph graph )
    {
        if ( visited.contains( from ) )
        {
            return;
        }

        visited.add( from );

        List<MetadataGraphEdge> exitList = graph.getExcidentEdges( from );
        // String s = "|---> "+from.getMd().toString()+" - "+(exitList == null ? -1 : exitList.size()) + " exit links";
        if ( exitList != null && exitList.size() > 0 )
        {
            for ( MetadataGraphEdge e : graph.getExcidentEdges( from ) )
            {
                visit( e.getTarget(), visited, graph );
            }
        }
    }
