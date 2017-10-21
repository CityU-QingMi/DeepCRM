    public void debugReactorPlan( ProjectBuildList projectBuilds )
    {
        if ( !logger.isDebugEnabled() )
        {
            return;
        }

        logger.debug( "=== REACTOR BUILD PLAN ================================================" );

        for ( Iterator<ProjectSegment> it = projectBuilds.iterator(); it.hasNext(); )
        {
            ProjectSegment projectBuild = it.next();

            logger.debug( "Project: " + projectBuild.getProject().getId() );
            logger.debug( "Tasks:   " + projectBuild.getTaskSegment().getTasks() );
            logger.debug( "Style:   " + ( projectBuild.getTaskSegment().isAggregating() ? "Aggregating" : "Regular" ) );

            if ( it.hasNext() )
            {
                logger.debug( "-----------------------------------------------------------------------" );
            }
        }

        logger.debug( "=======================================================================" );
    }
