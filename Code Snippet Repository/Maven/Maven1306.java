    public int doMain( CliRequest cliRequest )
    {
        PlexusContainer localContainer = null;
        try
        {
            initialize( cliRequest );
            cli( cliRequest );
            logging( cliRequest );
            version( cliRequest );
            properties( cliRequest );
            localContainer = container( cliRequest );
            commands( cliRequest );
            configure( cliRequest );
            toolchains( cliRequest );
            populateRequest( cliRequest );
            encryption( cliRequest );
            repository( cliRequest );
            return execute( cliRequest );
        }
        catch ( ExitException e )
        {
            return e.exitCode;
        }
        catch ( UnrecognizedOptionException e )
        {
            // pure user error, suppress stack trace
            return 1;
        }
        catch ( BuildAbort e )
        {
            CLIReportingUtils.showError( slf4jLogger, "ABORTED", e, cliRequest.showErrors );

            return 2;
        }
        catch ( Exception e )
        {
            CLIReportingUtils.showError( slf4jLogger, "Error executing Maven.", e, cliRequest.showErrors );

            return 1;
        }
        finally
        {
            if ( localContainer != null )
            {
                localContainer.dispose();
            }
        }
    }
