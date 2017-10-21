    public List<TaskSegment> calculateTaskSegments( MavenSession session )
        throws PluginNotFoundException, PluginResolutionException, PluginDescriptorParsingException,
        MojoNotFoundException, NoPluginFoundForPrefixException, InvalidPluginDescriptorException,
        PluginVersionResolutionException, LifecyclePhaseNotFoundException, LifecycleNotFoundException
    {

        MavenProject rootProject = session.getTopLevelProject();

        List<String> tasks = session.getGoals();

        if ( ( tasks == null || tasks.isEmpty() ) && !StringUtils.isEmpty( rootProject.getDefaultGoal() ) )
        {
            tasks = Arrays.asList( StringUtils.split( rootProject.getDefaultGoal() ) );
        }

        return calculateTaskSegments( session, tasks );
    }
