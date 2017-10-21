    @Deprecated
    public int getNumberOfExceutions( ProjectBuildList projectBuildList )
        throws InvalidPluginDescriptorException, PluginVersionResolutionException, PluginDescriptorParsingException,
        NoPluginFoundForPrefixException, MojoNotFoundException, PluginNotFoundException, PluginResolutionException,
        LifecyclePhaseNotFoundException, LifecycleNotFoundException
    {
        int result = 0;
        for ( ProjectSegment projectBuild : projectBuildList )
        {
            MavenExecutionPlan plan = calculateExecutionPlan( projectBuild.getSession(), projectBuild.getProject(),
                                                              projectBuild.getTaskSegment().getTasks() );
            result += plan.size();
        }
        return result;
    }
