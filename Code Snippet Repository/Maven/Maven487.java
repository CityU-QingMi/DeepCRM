    private static org.apache.maven.artifact.Artifact toArtifact( Dependency dependency )
    {
        if ( dependency == null )
        {
            return null;
        }

        org.apache.maven.artifact.Artifact result = toArtifact( dependency.getArtifact() );
        result.setScope( dependency.getScope() );
        result.setOptional( dependency.isOptional() );

        return result;
    }
