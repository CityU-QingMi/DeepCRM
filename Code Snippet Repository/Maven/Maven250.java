    private ArtifactMetadata createMetadata( Artifact artifact )
    {
        Versioning versioning = new Versioning();
        versioning.updateTimestamp();
        versioning.addVersion( artifact.getVersion() );

        if ( artifact.isRelease() )
        {
            versioning.setRelease( artifact.getVersion() );
        }

        return new ArtifactRepositoryMetadata( artifact, versioning );
    }
