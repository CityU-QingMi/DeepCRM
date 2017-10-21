    private void commands( CliRequest cliRequest )
    {
        if ( cliRequest.showErrors )
        {
            slf4jLogger.info( "Error stacktraces are turned on." );
        }

        if ( MavenExecutionRequest.CHECKSUM_POLICY_WARN.equals( cliRequest.request.getGlobalChecksumPolicy() ) )
        {
            slf4jLogger.info( "Disabling strict checksum verification on all artifact downloads." );
        }
        else if ( MavenExecutionRequest.CHECKSUM_POLICY_FAIL.equals( cliRequest.request.getGlobalChecksumPolicy() ) )
        {
            slf4jLogger.info( "Enabling strict checksum verification on all artifact downloads." );
        }

        if ( slf4jLogger.isDebugEnabled() )
        {
            slf4jLogger.debug( "Message scheme: " + ( MessageUtils.isColorEnabled() ? "color" : "plain" ) );
            if ( MessageUtils.isColorEnabled() )
            {
                MessageBuilder buff = MessageUtils.buffer();
                buff.a( "Message styles: " );
                buff.debug( "debug" ).a( ' ' );
                buff.info( "info" ).a( ' ' );
                buff.warning( "warning" ).a( ' ' );
                buff.error( "error" ).a( ' ' );
                buff.success( "success" ).a( ' ' );
                buff.failure( "failure" ).a( ' ' );
                buff.strong( "strong" ).a( ' ' );
                buff.mojo( "mojo" ).a( ' ' );
                buff.project( "project" );
                slf4jLogger.debug( buff.toString() );
            }
        }
    }
