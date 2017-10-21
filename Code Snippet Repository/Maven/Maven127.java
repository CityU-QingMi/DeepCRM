    public boolean include( Artifact artifact )
    {
        for ( ArtifactFilter filter : filters )
        {
            if ( filter.include( artifact ) )
            {
                return true;
            }
        }

        return false;
    }
