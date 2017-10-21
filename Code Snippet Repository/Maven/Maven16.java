    protected void setBaseVersionInternal( String baseVersion )
    {
        Matcher m = VERSION_FILE_PATTERN.matcher( baseVersion );

        if ( m.matches() )
        {
            this.baseVersion = m.group( 1 ) + "-" + SNAPSHOT_VERSION;
        }
        else
        {
            this.baseVersion = baseVersion;
        }
    }
