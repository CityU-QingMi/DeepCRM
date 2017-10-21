    private void getUpstreamProjects( String projectId, Collection<String> projectIds, boolean transitive )
    {
        for ( String id : sorter.getDependencies( projectId ) )
        {
            if ( projectIds.add( id ) && transitive )
            {
                getUpstreamProjects( id, projectIds, transitive );
            }
        }
    }
