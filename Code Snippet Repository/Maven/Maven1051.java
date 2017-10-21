    public void testWithTransitiveOnly()
        throws CycleDetectedException, DuplicateProjectException
    {
        final ProjectDependencyGraph graph = new DefaultProjectDependencyGraph(
            Arrays.asList( depender1, transitiveOnly, depender2, depender3, aProject ) );

        final List<MavenProject> downstreamProjects = graph.getDownstreamProjects( aProject, true );
        assertEquals( depender1, downstreamProjects.get( 0 ) );
        assertEquals( depender3, downstreamProjects.get( 1 ) );
        assertEquals( transitiveOnly, downstreamProjects.get( 2 ) );
        assertEquals( depender2, downstreamProjects.get( 3 ) );
    }
