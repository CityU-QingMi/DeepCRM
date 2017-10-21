    private void selectVersionFromNewRangeIfAvailable()
    {
        if ( ( versionRange != null ) && ( versionRange.getRecommendedVersion() != null ) )
        {
            selectVersion( versionRange.getRecommendedVersion().toString() );
        }
        else
        {
            version = null;
            baseVersion = null;
        }
    }
