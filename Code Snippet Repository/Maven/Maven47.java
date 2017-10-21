    public ArtifactVersion getSelectedVersion( Artifact artifact )
        throws OverConstrainedVersionException
    {
        ArtifactVersion version;
        if ( recommendedVersion != null )
        {
            version = recommendedVersion;
        }
        else
        {
            if ( restrictions.size() == 0 )
            {
                throw new OverConstrainedVersionException( "The artifact has no valid ranges", artifact );
            }

            version = null;
        }
        return version;
    }
