    public void testFormatDuration()
    {
        assertEquals( "0.001 s", CLIReportingUtils.formatDuration( 1 ) );
        assertEquals( "0.999 s", CLIReportingUtils.formatDuration( 1000 - 1 ) );
        assertEquals( "1.000 s", CLIReportingUtils.formatDuration( 1000 ) );
        assertEquals( "59.999 s", CLIReportingUtils.formatDuration( 60 * 1000 - 1 ) );
        assertEquals( "01:00 min", CLIReportingUtils.formatDuration( 60 * 1000 ) );
        assertEquals( "59:59 min", CLIReportingUtils.formatDuration( 60 * 60 * 1000 - 1 ) );
        assertEquals( "01:00 h", CLIReportingUtils.formatDuration( 60 * 60 * 1000 ) );
        assertEquals( "23:59 h", CLIReportingUtils.formatDuration( 24 * 60 * 60 * 1000 - 1 ) );
        assertEquals( "1 d 00:00 h", CLIReportingUtils.formatDuration( 24 * 60 * 60 * 1000 ) );
    }
