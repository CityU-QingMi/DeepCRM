    @Override
    public void forkedProjectStarted( ExecutionEvent event )
    {
        if ( logger.isInfoEnabled() && event.getMojoExecution().getForkedExecutions().size() > 1 )
        {
            logger.info( "" );
            infoLine( '>' );

            infoMain( "Forking " + event.getProject().getName() + " " + event.getProject().getVersion() );

            infoLine( '>' );
        }
    }
