    public void testIdentityProtoInheritance()
    {
        Parent parent = new Parent();

        parent.setGroupId( "test-group" );
        parent.setVersion( "1000" );
        parent.setArtifactId( "test-artifact" );

        Model model = new Model();

        model.setParent( parent );
        model.setArtifactId( "real-artifact" );

        MavenProject project = new MavenProject( model );

        assertEquals( "groupId proto-inheritance failed.", "test-group", project.getGroupId() );
        assertEquals( "artifactId is masked.", "real-artifact", project.getArtifactId() );
        assertEquals( "version proto-inheritance failed.", "1000", project.getVersion() );

        // draw the NPE.
        project.getId();
    }
