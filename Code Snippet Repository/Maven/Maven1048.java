    public void testVerifyExpectedParentStructure()
        throws CycleDetectedException, DuplicateProjectException
    {
        // This test verifies the baseline structure used in subsequent tests. If this fails, the rest will fail.
        ProjectDependencyGraph graph = threeProjectsDependingOnASingle();
        final List<MavenProject> sortedProjects = graph.getSortedProjects();
        assertEquals( aProject, sortedProjects.get( 0 ) );
        assertEquals( depender1, sortedProjects.get( 1 ) );
        assertEquals( depender2, sortedProjects.get( 2 ) );
        assertEquals( depender3, sortedProjects.get( 3 ) );
    }
