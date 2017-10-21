    public static Artifact toArtifact( org.apache.maven.artifact.Artifact artifact )
    {
        if ( artifact == null )
        {
            return null;
        }

        String version = artifact.getVersion();
        if ( version == null && artifact.getVersionRange() != null )
        {
            version = artifact.getVersionRange().toString();
        }

        Map<String, String> props = null;
        if ( org.apache.maven.artifact.Artifact.SCOPE_SYSTEM.equals( artifact.getScope() ) )
        {
            String localPath = ( artifact.getFile() != null ) ? artifact.getFile().getPath() : "";
            props = Collections.singletonMap( ArtifactProperties.LOCAL_PATH, localPath );
        }

        Artifact result =
            new DefaultArtifact( artifact.getGroupId(), artifact.getArtifactId(), artifact.getClassifier(),
                                 artifact.getArtifactHandler().getExtension(), version, props,
                                 newArtifactType( artifact.getType(), artifact.getArtifactHandler() ) );
        result = result.setFile( artifact.getFile() );

        return result;
    }
