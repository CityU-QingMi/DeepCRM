    public int getNature()
    {
        if ( artifact.getVersion() != null )
        {
            return artifact.isSnapshot() ? SNAPSHOT : RELEASE;
        }

        VersionRange range = artifact.getVersionRange();
        if ( range != null )
        {
            for ( Restriction restriction : range.getRestrictions() )
            {
                if ( isSnapshot( restriction.getLowerBound() ) || isSnapshot( restriction.getUpperBound() ) )
                {
                    return RELEASE_OR_SNAPSHOT;
                }
            }
        }

        return RELEASE;
    }
