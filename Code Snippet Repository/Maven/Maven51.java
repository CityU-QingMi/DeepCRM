    public boolean containsVersion( ArtifactVersion version )
    {
        for ( Restriction restriction : restrictions )
        {
            if ( restriction.containsVersion( version ) )
            {
                return true;
            }
        }
        return false;
    }
