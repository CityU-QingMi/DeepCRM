    public static void showError( Logger logger, String message, Throwable e, boolean showStackTrace )
    {
        if ( showStackTrace )
        {
            logger.error( message, e );
        }
        else
        {
            logger.error( message );

            if ( e != null )
            {
                logger.error( e.getMessage() );

                for ( Throwable cause = e.getCause(); cause != null; cause = cause.getCause() )
                {
                    logger.error( "Caused by: " + cause.getMessage() );
                }
            }
        }
    }
