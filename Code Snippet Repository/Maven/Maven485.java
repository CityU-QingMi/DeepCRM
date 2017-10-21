    private Artifact findMatchingArtifact( MavenProject project, Artifact requestedArtifact )
    {
        String requestedRepositoryConflictId = ArtifactIdUtils.toVersionlessId( requestedArtifact );

        Artifact mainArtifact = RepositoryUtils.toArtifact( project.getArtifact() );
        if ( requestedRepositoryConflictId.equals( ArtifactIdUtils.toVersionlessId( mainArtifact ) ) )
        {
            return mainArtifact;
        }

        for ( Artifact attachedArtifact : RepositoryUtils.toArtifacts( project.getAttachedArtifacts() ) )
        {
            if ( attachedArtifactComparison( requestedArtifact, attachedArtifact ) )
            {
                return attachedArtifact;
            }
        }

        return null;
    }
