    public String getArtifactId()
    {
        if ( artifactId != null )
        {
            return artifactId;
        }
        else
        {
            return artifact.getArtifactId();
        }
    }
