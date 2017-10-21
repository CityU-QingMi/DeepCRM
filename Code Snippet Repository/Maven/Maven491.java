    public static org.apache.maven.artifact.Artifact toArtifact( Artifact artifact )
    {
        if ( artifact == null )
        {
            return null;
        }

        ArtifactHandler handler = newHandler( artifact );

/**/
/**/
/**/
/**/
        org.apache.maven.artifact.Artifact result =
            new org.apache.maven.artifact.DefaultArtifact( artifact.getGroupId(), artifact.getArtifactId(),
                                                           artifact.getVersion(), null,
                                                           artifact.getProperty( ArtifactProperties.TYPE,
                                                                                 artifact.getExtension() ),
                                                           nullify( artifact.getClassifier() ), handler );

        result.setFile( artifact.getFile() );
        result.setResolved( artifact.getFile() != null );

        List<String> trail = new ArrayList<>( 1 );
        trail.add( result.getId() );
        result.setDependencyTrail( trail );

        return result;
    }
