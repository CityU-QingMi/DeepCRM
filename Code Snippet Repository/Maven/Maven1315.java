    @Override
    public void forkSucceeded( ExecutionEvent event )
    {
        if ( logger.isInfoEnabled() )
        {
            logger.info( "" );

            MessageBuilder buffer = buffer().strong( "<<< " );
            append( buffer, event.getMojoExecution() );
            buffer.strong( " < " );
            appendForkInfo( buffer, event.getMojoExecution().getMojoDescriptor() );
            append( buffer, event.getProject() );
            buffer.strong( " <<<" );

            logger.info( buffer.toString() );

            logger.info( "" );
        }
    }
