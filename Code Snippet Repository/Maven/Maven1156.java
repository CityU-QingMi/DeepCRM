    public void testShouldInterpretChildPathAdjustmentBasedOnModulePaths()
        throws IOException
    {
        Model parentModel = new Model();
        parentModel.addModule( "../child" );

        MavenProject parentProject = new MavenProject( parentModel );

        Model childModel = new Model();
        childModel.setArtifactId( "artifact" );

        MavenProject childProject = new MavenProject( childModel );

        File childFile =
            new File( System.getProperty( "java.io.tmpdir" ), "maven-project-tests" + System.currentTimeMillis()
                + "/child/pom.xml" );

        childProject.setFile( childFile );

        String adjustment = parentProject.getModulePathAdjustment( childProject );

        assertNotNull( adjustment );

        assertEquals( "..", adjustment );
    }
