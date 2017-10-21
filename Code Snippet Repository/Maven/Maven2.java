    public static boolean isSnapshot( String version )
    {
        if ( version != null )
        {
            if ( version.regionMatches( true, version.length() - Artifact.SNAPSHOT_VERSION.length(),
                                        Artifact.SNAPSHOT_VERSION, 0, Artifact.SNAPSHOT_VERSION.length() ) )
            {
                return true;
            }
            else if ( Artifact.VERSION_FILE_PATTERN.matcher( version ).matches() )
            {
                return true;
            }
        }
        return false;
    }
