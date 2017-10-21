    public void attachArtifact( MavenProject project, File artifactFile, String artifactClassifier )
    {
        Artifact projectArtifact = project.getArtifact();

        Artifact artifact =
            new AttachedArtifact( projectArtifact, projectArtifact.getType(), artifactClassifier,
                                  projectArtifact.getArtifactHandler() );

        artifact.setFile( artifactFile );
        artifact.setResolved( true );

        attachArtifact( project, artifact );
    }
