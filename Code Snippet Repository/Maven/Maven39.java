    @Override
    public boolean equals( Object other )
    {
        if ( this == other )
        {
            return true;
        }

        if ( !( other instanceof ArtifactVersion ) )
        {
            return false;
        }

        return compareTo( (ArtifactVersion) other ) == 0;
    }
