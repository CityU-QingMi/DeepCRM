    public List<MetadataGraphEdge> getEdgesBetween( MetadataGraphVertex vFrom, MetadataGraphVertex vTo )
    {
        List<MetadataGraphEdge> edges = getIncidentEdges( vTo );
        if ( edges == null || edges.isEmpty() )
        {
            return null;
        }

        List<MetadataGraphEdge> res = new ArrayList<>( edges.size() );

        for ( MetadataGraphEdge e : edges )
        {
            if ( e.getSource().equals( vFrom ) )
            {
                res.add( e );
            }
        }

        return res;
    }
