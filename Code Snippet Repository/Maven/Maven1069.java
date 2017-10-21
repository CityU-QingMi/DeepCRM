    public void testConcurrencyGraphPrimaryVersion()
        throws InvalidPluginDescriptorException, PluginVersionResolutionException, PluginDescriptorParsingException,
        NoPluginFoundForPrefixException, MojoNotFoundException, PluginNotFoundException, PluginResolutionException,
        LifecyclePhaseNotFoundException, LifecycleNotFoundException
    {
        ProjectDependencyGraph dependencyGraph = new ProjectDependencyGraphStub();
        final MavenSession session = ProjectDependencyGraphStub.getMavenSession();

        ConcurrencyDependencyGraph graph =
            new ConcurrencyDependencyGraph( getProjectBuildList( session ), dependencyGraph );

        final List<MavenProject> projectBuilds = graph.getRootSchedulableBuilds();
        assertEquals( 1, projectBuilds.size() );
        assertEquals( A, projectBuilds.get( 0 ) );

        final List<MavenProject> subsequent = graph.markAsFinished( A );
        assertEquals( 2, subsequent.size() );
        assertEquals( ProjectDependencyGraphStub.B, subsequent.get( 0 ) );
        assertEquals( C, subsequent.get( 1 ) );

        final List<MavenProject> bDescendants = graph.markAsFinished( B );
        assertEquals( 1, bDescendants.size() );
        assertEquals( Y, bDescendants.get( 0 ) );

        final List<MavenProject> cDescendants = graph.markAsFinished( C );
        assertEquals( 2, cDescendants.size() );
        assertEquals( X, cDescendants.get( 0 ) );
        assertEquals( Z, cDescendants.get( 1 ) );
    }
