    @Inject
    public ReactorReader( MavenSession session )
    {
        projectsByGAV = session.getProjectMap();

        projectsByGA = new HashMap<>( projectsByGAV.size() * 2 );
        for ( MavenProject project : projectsByGAV.values() )
        {
            String key = ArtifactUtils.versionlessKey( project.getGroupId(), project.getArtifactId() );

            List<MavenProject> projects = projectsByGA.get( key );

            if ( projects == null )
            {
                projects = new ArrayList<>( 1 );
                projectsByGA.put( key, projects );
            }

            projects.add( project );
        }

        repository = new WorkspaceRepository( "reactor", new HashSet<>( projectsByGAV.keySet() ) );
    }
