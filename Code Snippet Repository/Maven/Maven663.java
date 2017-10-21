    private void getDownstreamProjects( String projectId, Set<String> projectIds, boolean transitive )
    {
        for ( String id : sorter.getDependents( projectId ) )
        {
            if ( projectIds.add( id ) && transitive )
            {
                getDownstreamProjects( id, projectIds, transitive );
            }
        }
    }
