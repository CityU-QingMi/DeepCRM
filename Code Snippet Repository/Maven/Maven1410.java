    public MavenBuildTimestamp( Date time, String timestampFormat )
    {
        if ( timestampFormat == null )
        {
            timestampFormat = DEFAULT_BUILD_TIMESTAMP_FORMAT;
        }
        if ( time == null )
        {
            time = new Date();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat( timestampFormat );
        dateFormat.setCalendar( new GregorianCalendar() );
        dateFormat.setTimeZone( DEFAULT_BUILD_TIME_ZONE );
        formattedTimestamp = dateFormat.format( time );
    }
