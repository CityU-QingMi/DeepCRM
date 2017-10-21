    public static List<ProjectSegment> getProjectBuilds( MavenSession session )
        throws InvalidPluginDescriptorException, PluginVersionResolutionException, PluginDescriptorParsingException,
        NoPluginFoundForPrefixException, PluginNotFoundException, MojoNotFoundException, PluginResolutionException,
        LifecyclePhaseNotFoundException, LifecycleNotFoundException
    {
        List<ProjectSegment> projectBuilds = new ArrayList<>();

        TaskSegment segment = createTaskSegment();
        projectBuilds.add( createProjectBuild( A, session, segment ) );
        projectBuilds.add( createProjectBuild( B, session, segment ) );
        projectBuilds.add( createProjectBuild( C, session, segment ) );
        projectBuilds.add( createProjectBuild( X, session, segment ) );
        projectBuilds.add( createProjectBuild( Y, session, segment ) );
        projectBuilds.add( createProjectBuild( Z, session, segment ) );
        return projectBuilds;
    }
