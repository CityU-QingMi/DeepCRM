    public void attachArtifact( MavenProject project, String artifactType, String artifactClassifier,
                                File artifactFile )
    {
        String type = artifactType;

        ArtifactHandler handler = null;

        if ( type != null )
        {
            handler = artifactHandlerManager.getArtifactHandler( artifactType );
        }

        if ( handler == null )
        {
            handler = artifactHandlerManager.getArtifactHandler( "jar" );
        }

        Artifact artifact = new AttachedArtifact( project.getArtifact(), artifactType, artifactClassifier, handler );

        artifact.setFile( artifactFile );
        artifact.setResolved( true );

        attachArtifact( project, artifact );
    }
