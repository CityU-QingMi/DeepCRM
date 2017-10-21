    @Override
    public boolean equals( Object obj )
    {
        if ( obj == this )
        {
            return true;
        }
        else if ( obj == null || !getClass().equals( obj.getClass() ) )
        {
            return false;
        }

        WagonExcluder that = (WagonExcluder) obj;
        return coreArtifact == that.coreArtifact;
    }
