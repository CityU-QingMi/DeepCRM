    private String pathOfRepositoryMetadata( ArtifactMetadata metadata,
                                             String filename )
    {
        StringBuilder path = new StringBuilder( 128 );

        path.append( formatAsDirectory( metadata.getGroupId() ) ).append( PATH_SEPARATOR );
        if ( !metadata.storedInGroupDirectory() )
        {
            path.append( metadata.getArtifactId() ).append( PATH_SEPARATOR );

            if ( metadata.storedInArtifactVersionDirectory() )
            {
                path.append( metadata.getBaseVersion() ).append( PATH_SEPARATOR );
            }
        }

        path.append( filename );

        return path.toString();
    }
