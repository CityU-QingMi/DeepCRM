    private static Metadata createRepositoryMetadata( Artifact artifact )
    {
        Metadata metadata = new Metadata();
        metadata.setGroupId( artifact.getGroupId() );
        metadata.setArtifactId( artifact.getArtifactId() );

        Versioning versioning = new Versioning();
        versioning.addVersion( artifact.getBaseVersion() );
        if ( !artifact.isSnapshot() )
        {
            versioning.setRelease( artifact.getBaseVersion() );
        }
        if ( "maven-plugin".equals( artifact.getProperty( ArtifactProperties.TYPE, "" ) ) )
        {
            versioning.setLatest( artifact.getBaseVersion() );
        }

        metadata.setVersioning( versioning );

        return metadata;
    }
