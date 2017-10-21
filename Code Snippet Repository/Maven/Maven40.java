    public int compareTo( ArtifactVersion otherVersion )
    {
        if ( otherVersion instanceof DefaultArtifactVersion )
        {
            return this.comparable.compareTo( ( (DefaultArtifactVersion) otherVersion ).comparable );
        }
        else
        {
            return compareTo( new DefaultArtifactVersion( otherVersion.toString() ) );
        }
    }
