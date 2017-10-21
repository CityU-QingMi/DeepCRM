    public ArtifactVersion matchVersion( List<ArtifactVersion> versions )
    {
        // TODO could be more efficient by sorting the list and then moving along the restrictions in order?

        ArtifactVersion matched = null;
        for ( ArtifactVersion version : versions )
        {
            if ( containsVersion( version ) )
            {
                // valid - check if it is greater than the currently matched version
                if ( matched == null || version.compareTo( matched ) > 0 )
                {
                    matched = version;
                }
            }
        }
        return matched;
    }
