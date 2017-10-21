    protected static Metadata createRepositoryMetadata( Artifact artifact, boolean legacyFormat )
    {
        Metadata metadata = new Metadata();
        if ( !legacyFormat )
        {
            metadata.setModelVersion( "1.1.0" );
        }
        metadata.setGroupId( artifact.getGroupId() );
        metadata.setArtifactId( artifact.getArtifactId() );
        metadata.setVersion( artifact.getBaseVersion() );

        return metadata;
    }
