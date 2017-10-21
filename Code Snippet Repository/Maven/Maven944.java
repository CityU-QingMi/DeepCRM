    private void addEdge( Vertex fromVertex, Vertex toVertex, MavenProject fromProject,
                          Map<String, MavenProject> projectMap, boolean force, boolean safe )
        throws CycleDetectedException
    {
        if ( fromVertex.equals( toVertex ) )
        {
            return;
        }

        if ( fromProject != null )
        {
            MavenProject toProject = projectMap.get( toVertex.getLabel() );
            fromProject.addProjectReference( toProject );
        }

        if ( force && toVertex.getChildren().contains( fromVertex ) )
        {
            dag.removeEdge( toVertex, fromVertex );
        }

        try
        {
            dag.addEdge( fromVertex, toVertex );
        }
        catch ( CycleDetectedException e )
        {
            if ( !safe )
            {
                throw e;
            }
        }
    }
