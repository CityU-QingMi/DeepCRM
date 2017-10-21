    public void attachArtifact( MavenProject project, String artifactType, File artifactFile )
    {
        ArtifactHandler handler = artifactHandlerManager.getArtifactHandler( artifactType );

        Artifact artifact = new AttachedArtifact( project.getArtifact(), artifactType, handler );

        artifact.setFile( artifactFile );
        artifact.setResolved( true );

        attachArtifact( project, artifact );
    }
