    public static String toSnapshotVersion( String version )
    {
        Validate.notBlank( version, "version can neither be null, empty nor blank" );

        Matcher m = Artifact.VERSION_FILE_PATTERN.matcher( version );
        if ( m.matches() )
        {
            return m.group( 1 ) + "-" + Artifact.SNAPSHOT_VERSION;
        }
        else
        {
            return version;
        }
    }
