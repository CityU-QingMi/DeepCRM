    public void testDefaultBuildTimestampFormatWithLocalTimeZoneMidnightRollover()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone( TimeZone.getTimeZone( "Europe/Berlin" ) );

        cal.set( Calendar.HOUR_OF_DAY, 1 );
        cal.set( Calendar.MINUTE, 16 );
        cal.set( Calendar.SECOND, 0 );
        cal.set( Calendar.YEAR, 2014 );
        cal.set( Calendar.MONTH, Calendar.JUNE );
        cal.set( Calendar.DATE, 16 );

        Date firstTestDate = cal.getTime();

        cal.set( Calendar.MONTH, Calendar.NOVEMBER );

        Date secondTestDate = cal.getTime();

        SimpleDateFormat format =
            new SimpleDateFormat( MavenBuildTimestamp.DEFAULT_BUILD_TIMESTAMP_FORMAT );
        format.setTimeZone( MavenBuildTimestamp.DEFAULT_BUILD_TIME_ZONE );
        assertEquals( "2014-06-15T23:16:00Z", format.format( firstTestDate ) );
        assertEquals( "2014-11-16T00:16:00Z", format.format( secondTestDate ) );
    }
