    private void logResult( MavenSession session )
    {
        infoLine( '-' );
        MessageBuilder buffer = buffer();

        if ( session.getResult().hasExceptions() )
        {
            buffer.failure( "BUILD FAILURE" );
        }
        else
        {
            buffer.success( "BUILD SUCCESS" );
        }
        logger.info( buffer.toString() );
    }
