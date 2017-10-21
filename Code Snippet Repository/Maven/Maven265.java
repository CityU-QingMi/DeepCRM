    private MetadataGraphEdge cleanEdges( MetadataGraphVertex v, List<MetadataGraphEdge> edges,
                                          ArtifactScopeEnum scope )
    {
        if ( edges == null || edges.isEmpty() )
        {
            return null;
        }

        if ( edges.size() == 1 )
        {
            MetadataGraphEdge e = edges.get( 0 );
            if ( scope.encloses( e.getScope() ) )
            {
                return e;
            }

            return null;
        }

        MetadataGraphEdge res = null;

        for ( MetadataGraphEdge e : edges )
        {
            if ( !scope.encloses( e.getScope() ) )
            {
                continue;
            }

            if ( res == null )
            {
                res = e;
            }
            else
            {
                res = policy.apply( e, res );
            }
        }

        return res;
    }
