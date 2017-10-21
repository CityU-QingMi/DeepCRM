    @Override
    public void build( MavenSession session, ReactorContext reactorContext, ProjectBuildList projectBuilds,
                       List<TaskSegment> taskSegments, ReactorBuildStatus reactorBuildStatus )
        throws ExecutionException, InterruptedException
    {
        int nThreads = Math.min( session.getRequest().getDegreeOfConcurrency(), session.getProjects().size() );
        boolean parallel = nThreads >= 2;
        // Propagate the parallel flag to the root session and all of the cloned sessions in each project segment
        session.setParallel( parallel );
        for ( ProjectSegment segment : projectBuilds )
        {
            segment.getSession().setParallel( parallel );
        }
        ExecutorService executor = Executors.newFixedThreadPool( nThreads, new BuildThreadFactory() );
        CompletionService<ProjectSegment> service = new ExecutorCompletionService<>( executor );
        ConcurrencyDependencyGraph analyzer =
            new ConcurrencyDependencyGraph( projectBuilds, session.getProjectDependencyGraph() );

        // Currently disabled
        ThreadOutputMuxer muxer = null; // new ThreadOutputMuxer( analyzer.getProjectBuilds(), System.out );

        for ( TaskSegment taskSegment : taskSegments )
        {
            Map<MavenProject, ProjectSegment> projectBuildMap = projectBuilds.selectSegment( taskSegment );
            try
            {
                multiThreadedProjectTaskSegmentBuild( analyzer, reactorContext, session, service, taskSegment,
                                                      projectBuildMap, muxer );
                if ( reactorContext.getReactorBuildStatus().isHalted() )
                {
                    break;
                }
            }
            catch ( Exception e )
            {
                session.getResult().addException( e );
                break;
            }

        }
    }
