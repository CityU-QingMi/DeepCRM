    private void setupMojoExecutions( MavenSession session, MavenProject project, List<MojoExecution> mojoExecutions )
        throws PluginNotFoundException, PluginResolutionException, PluginDescriptorParsingException,
        MojoNotFoundException, InvalidPluginDescriptorException, NoPluginFoundForPrefixException,
        LifecyclePhaseNotFoundException, LifecycleNotFoundException, PluginVersionResolutionException
    {
        for ( MojoExecution mojoExecution : mojoExecutions )
        {
            setupMojoExecution( session, project, mojoExecution );
        }
    }
