    public boolean isSelectedVersionKnown( Artifact artifact )
        throws OverConstrainedVersionException
    {
        boolean value = false;
        if ( recommendedVersion != null )
        {
            value = true;
        }
        else
        {
            if ( restrictions.size() == 0 )
            {
                throw new OverConstrainedVersionException( "The artifact has no valid ranges", artifact );
            }
        }
        return value;
    }
