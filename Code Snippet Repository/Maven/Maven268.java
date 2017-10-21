    public MetadataGraph addEdge( MetadataGraphVertex vFrom, MetadataGraphVertex vTo, MetadataGraphEdge e )
        throws MetadataResolutionException
    {
        checkVertex( vFrom );
        checkVertex( vTo );

        checkVertices();

        checkEdge( e );
        checkEdges();

        e.setSource( vFrom );
        e.setTarget( vTo );

        vFrom.setCompareVersion( versionedVertices );
        vFrom.setCompareScope( scopedVertices );

        List<MetadataGraphEdge> exList = excidentEdges.get( vFrom );
        if ( exList == null )
        {
            exList = new ArrayList<>();
            excidentEdges.put( vFrom, exList );
        }

        if ( !exList.contains( e ) )
        {
            exList.add( e );
        }

        List<MetadataGraphEdge> inList = incidentEdges.get( vTo );
        if ( inList == null )
        {
            inList = new ArrayList<>();
            incidentEdges.put( vTo, inList );
        }

        if ( !inList.contains( e ) )
        {
            inList.add( e );
        }

        return this;
    }
