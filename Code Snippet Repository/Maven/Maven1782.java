    public static Artifact toPomArtifact( Artifact artifact )
    {
        Artifact pomArtifact = artifact;

        if ( pomArtifact.getClassifier().length() > 0 || !"pom".equals( pomArtifact.getExtension() ) )
        {
            pomArtifact =
                new DefaultArtifact( artifact.getGroupId(), artifact.getArtifactId(), "pom", artifact.getVersion() );
        }

        return pomArtifact;
    }
