    public static ArtifactHandler newHandler( Artifact artifact )
    {
        String type = artifact.getProperty( ArtifactProperties.TYPE, artifact.getExtension() );
        DefaultArtifactHandler handler = new DefaultArtifactHandler( type );
        handler.setExtension( artifact.getExtension() );
        handler.setLanguage( artifact.getProperty( ArtifactProperties.LANGUAGE, null ) );
        String addedToClasspath = artifact.getProperty( ArtifactProperties.CONSTITUTES_BUILD_PATH, "" );
        handler.setAddedToClasspath( Boolean.parseBoolean( addedToClasspath ) );
        String includesDependencies = artifact.getProperty( ArtifactProperties.INCLUDES_DEPENDENCIES, "" );
        handler.setIncludesDependencies( Boolean.parseBoolean( includesDependencies ) );
        return handler;
    }
