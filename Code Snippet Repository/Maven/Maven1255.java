    private String pathOfRepositoryMetadata( ArtifactMetadata metadata,
                                             String filename )
    {
        StringBuilder path = new StringBuilder( 128 );

        path.append( metadata.getGroupId() ).append( PATH_SEPARATOR ).append( "poms" ).append( PATH_SEPARATOR );

        path.append( filename );

        return path.toString();
    }
