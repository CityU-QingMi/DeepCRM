    public void testDefaultBuildTimestampFormatShouldFormatTimeIn24HourFormat()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone( MavenBuildTimestamp.DEFAULT_BUILD_TIME_ZONE );
        cal.set( Calendar.HOUR, 12 );
        cal.set( Calendar.AM_PM, Calendar.AM );

        // just to make sure all the bases are covered...
        cal.set( Calendar.HOUR_OF_DAY, 0 );
        cal.set( Calendar.MINUTE, 16 );
        cal.set( Calendar.SECOND, 0 );
        cal.set( Calendar.YEAR, 1976 );
        cal.set( Calendar.MONTH, Calendar.NOVEMBER );
        cal.set( Calendar.DATE, 11 );

        Date firstTestDate = cal.getTime();

        cal.set( Calendar.HOUR, 11 );
        cal.set( Calendar.AM_PM, Calendar.PM );

        // just to make sure all the bases are covered...
        cal.set( Calendar.HOUR_OF_DAY, 23 );

        Date secondTestDate = cal.getTime();

        SimpleDateFormat format =
            new SimpleDateFormat( MavenBuildTimestamp.DEFAULT_BUILD_TIMESTAMP_FORMAT );
        format.setTimeZone( MavenBuildTimestamp.DEFAULT_BUILD_TIME_ZONE );
        assertEquals( "1976-11-11T00:16:00Z", format.format( firstTestDate ) );
        assertEquals( "1976-11-11T23:16:00Z", format.format( secondTestDate ) );
    }
