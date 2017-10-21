    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }

        // make sure IncludesArtifactFilter is not equal ExcludesArtifactFilter!
        if ( obj == null || getClass() != obj.getClass() )
        {
            return false;
        }

        IncludesArtifactFilter other = (IncludesArtifactFilter) obj;

        return patterns.equals( other.patterns );
    }
