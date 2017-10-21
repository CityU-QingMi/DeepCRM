    public void testWithMissingTransitiveOnly()
        throws CycleDetectedException, DuplicateProjectException
    {
        final ProjectDependencyGraph graph = new DefaultProjectDependencyGraph(
            Arrays.asList( depender1, transitiveOnly, depender2, depender3, aProject ) );

        final List<MavenProject> downstreamProjects = graph.getDownstreamProjects( aProject, false );
        assertEquals( depender1, downstreamProjects.get( 0 ) );
        assertEquals( depender3, downstreamProjects.get( 1 ) );
        assertEquals( depender2, downstreamProjects.get( 2 ) );
    }
