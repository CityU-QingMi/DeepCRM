    private static Metadata createMetadata( Artifact artifact, boolean legacyFormat )
    {
        Snapshot snapshot = new Snapshot();
        snapshot.setLocalCopy( true );
        Versioning versioning = new Versioning();
        versioning.setSnapshot( snapshot );

        Metadata metadata = new Metadata();
        metadata.setVersioning( versioning );
        metadata.setGroupId( artifact.getGroupId() );
        metadata.setArtifactId( artifact.getArtifactId() );
        metadata.setVersion( artifact.getBaseVersion() );

        if ( !legacyFormat )
        {
            metadata.setModelVersion( "1.1.0" );
        }

        return metadata;
    }
