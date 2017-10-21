    private void logError( String action, Throwable e, EventSpy spy )
    {
        String msg = "Failed to " + action + " spy " + spy.getClass().getName() + ": " + e.getMessage();

        if ( logger.isDebugEnabled() )
        {
            logger.warn( msg, e );
        }
        else
        {
            logger.warn( msg );
        }
    }
