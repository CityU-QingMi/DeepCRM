    @Override
    public void mojoStarted( ExecutionEvent event )
    {
        if ( logger.isInfoEnabled() )
        {
            logger.info( "" );

            MessageBuilder buffer = buffer().strong( "--- " );
            append( buffer, event.getMojoExecution() );
            append( buffer, event.getProject() );
            buffer.strong( " ---" );

            logger.info( buffer.toString() );
        }
    }
