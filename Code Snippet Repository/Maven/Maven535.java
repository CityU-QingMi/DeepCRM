    public boolean filterTrail( ArtifactFilter filter )
        throws OverConstrainedVersionException
    {
        boolean success = true;
        if ( filter != null )
        {
            for ( Artifact artifact : getTrail() )
            {
                if ( !filter.include( artifact ) )
                {
                    success = false;
                }
            }
        }
        return success;
    }
