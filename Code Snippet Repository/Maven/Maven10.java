    private void validateIdentity()
    {
        if ( empty( groupId ) )
        {
            throw new InvalidArtifactRTException( groupId, artifactId, getVersion(), type,
                "The groupId cannot be empty." );
        }

        if ( artifactId == null )
        {
            throw new InvalidArtifactRTException( groupId, artifactId, getVersion(), type,
                "The artifactId cannot be empty." );
        }

        if ( type == null )
        {
            throw new InvalidArtifactRTException( groupId, artifactId, getVersion(), type,
                "The type cannot be empty." );
        }

        if ( ( version == null ) && ( versionRange == null ) )
        {
            throw new InvalidArtifactRTException( groupId, artifactId, getVersion(), type,
                "The version cannot be empty." );
        }
    }
