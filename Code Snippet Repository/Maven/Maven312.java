    public void testArtifactInstallation()
        throws Exception
    {
        String artifactBasedir = new File( getBasedir(), "src/test/resources/artifact-install" ).getAbsolutePath();

        Artifact artifact = createArtifact( "artifact", "1.0" );

        File source = new File( artifactBasedir, "artifact-1.0.jar" );

        artifactInstaller.install( source, artifact, localRepository() );

        assertLocalArtifactPresent( artifact );
    }
