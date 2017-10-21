    public ProjectBuildList calculateProjectBuilds( MavenSession session, List<TaskSegment> taskSegments )
    {
        List<ProjectSegment> projectBuilds = new ArrayList<>();

        MavenProject rootProject = session.getTopLevelProject();

        for ( TaskSegment taskSegment : taskSegments )
        {
            List<MavenProject> projects;

            if ( taskSegment.isAggregating() )
            {
                projects = Collections.singletonList( rootProject );
            }
            else
            {
                projects = session.getProjects();
            }
            for ( MavenProject project : projects )
            {
                BuilderCommon.attachToThread( project ); // Not totally sure if this is needed for anything
                MavenSession copiedSession = session.clone();
                copiedSession.setCurrentProject( project );
                projectBuilds.add( new ProjectSegment( project, taskSegment, copiedSession ) );
            }
        }
        return new ProjectBuildList( projectBuilds );
    }
