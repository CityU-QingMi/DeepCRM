    public void testShouldNotFailWhenProjectReferencesNonExistentProject()
        throws CycleDetectedException, DuplicateProjectException
    {
        MavenProject project = createProject( "group", "artifact", "1.0" );

        Build build = project.getModel().getBuild();

        Extension extension = createExtension( "other.group", "other-artifact", "1.0" );

        build.addExtension( extension );

        new ProjectSorter( Collections.singletonList( project ) );
    }
