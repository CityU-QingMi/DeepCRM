    private PersistedToolchains readToolchains( Source toolchainsSource, ToolchainsBuildingRequest request,
                                                ProblemCollector problems )
    {
        if ( toolchainsSource == null )
        {
            return new PersistedToolchains();
        }

        PersistedToolchains toolchains;

        try
        {
            Map<String, ?> options = Collections.singletonMap( ToolchainsReader.IS_STRICT, Boolean.TRUE );

            try
            {
                toolchains = toolchainsReader.read( toolchainsSource.getInputStream(), options );
            }
            catch ( ToolchainsParseException e )
            {
                options = Collections.singletonMap( ToolchainsReader.IS_STRICT, Boolean.FALSE );

                toolchains = toolchainsReader.read( toolchainsSource.getInputStream(), options );

                problems.add( Problem.Severity.WARNING, e.getMessage(), e.getLineNumber(), e.getColumnNumber(),
                              e );
            }
        }
        catch ( ToolchainsParseException e )
        {
            problems.add( Problem.Severity.FATAL, "Non-parseable toolchains " + toolchainsSource.getLocation()
                + ": " + e.getMessage(), e.getLineNumber(), e.getColumnNumber(), e );
            return new PersistedToolchains();
        }
        catch ( IOException e )
        {
            problems.add( Problem.Severity.FATAL, "Non-readable toolchains " + toolchainsSource.getLocation()
                + ": " + e.getMessage(), -1, -1, e );
            return new PersistedToolchains();
        }

        return toolchains;
    }
