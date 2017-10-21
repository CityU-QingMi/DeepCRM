    public void testConcurrencyGraphDifferentCompletionOrder()
        throws InvalidPluginDescriptorException, PluginVersionResolutionException, PluginDescriptorParsingException,
        NoPluginFoundForPrefixException, MojoNotFoundException, PluginNotFoundException, PluginResolutionException,
        LifecyclePhaseNotFoundException, LifecycleNotFoundException
    {
        ProjectDependencyGraph dependencyGraph = new ProjectDependencyGraphStub();
        final MavenSession session = ProjectDependencyGraphStub.getMavenSession();
        ConcurrencyDependencyGraph graph =
            new ConcurrencyDependencyGraph( getProjectBuildList( session ), dependencyGraph );

        graph.markAsFinished( A );
        final List<MavenProject> cDescendants = graph.markAsFinished( C );
        assertEquals( 1, cDescendants.size() );
        assertEquals( Z, cDescendants.get( 0 ) );

        final List<MavenProject> bDescendants = graph.markAsFinished( B );
        assertEquals( 2, bDescendants.size() );
        assertEquals( X, bDescendants.get( 0 ) );
        assertEquals( Y, bDescendants.get( 1 ) );
    }
