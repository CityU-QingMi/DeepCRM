    private void logStats( MavenSession session )
    {
        infoLine( '-' );

        long finish = System.currentTimeMillis();

        long time = finish - session.getRequest().getStartTime().getTime();

        String wallClock = session.getRequest().getDegreeOfConcurrency() > 1 ? " (Wall Clock)" : "";

        logger.info( "Total time: " + formatDuration( time ) + wallClock );

        logger.info( "Finished at: " + formatTimestamp( finish ) );

        System.gc();

        Runtime r = Runtime.getRuntime();

        long mb = 1024 * 1024;

        logger.info( "Final Memory: " + ( r.totalMemory() - r.freeMemory() ) / mb + "M/" + r.totalMemory() / mb + "M" );
    }
