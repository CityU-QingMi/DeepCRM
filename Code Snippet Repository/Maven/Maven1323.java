    @Override
    public void projectStarted( ExecutionEvent event )
    {
        if ( logger.isInfoEnabled() )
        {
            logger.info( "" );
            infoLine( '-' );

            infoMain( "Building " + event.getProject().getName() + " " + event.getProject().getVersion() );

            infoLine( '-' );
        }
    }
