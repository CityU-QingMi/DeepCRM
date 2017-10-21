    private Settings readSettings( Source settingsSource, SettingsBuildingRequest request,
                                   DefaultSettingsProblemCollector problems )
    {
        if ( settingsSource == null )
        {
            return new Settings();
        }

        problems.setSource( settingsSource.getLocation() );

        Settings settings;

        try
        {
            Map<String, ?> options = Collections.singletonMap( SettingsReader.IS_STRICT, Boolean.TRUE );

            try
            {
                settings = settingsReader.read( settingsSource.getInputStream(), options );
            }
            catch ( SettingsParseException e )
            {
                options = Collections.singletonMap( SettingsReader.IS_STRICT, Boolean.FALSE );

                settings = settingsReader.read( settingsSource.getInputStream(), options );

                problems.add( SettingsProblem.Severity.WARNING, e.getMessage(), e.getLineNumber(), e.getColumnNumber(),
                              e );
            }
        }
        catch ( SettingsParseException e )
        {
            problems.add( SettingsProblem.Severity.FATAL, "Non-parseable settings " + settingsSource.getLocation()
                + ": " + e.getMessage(), e.getLineNumber(), e.getColumnNumber(), e );
            return new Settings();
        }
        catch ( IOException e )
        {
            problems.add( SettingsProblem.Severity.FATAL, "Non-readable settings " + settingsSource.getLocation()
                + ": " + e.getMessage(), -1, -1, e );
            return new Settings();
        }

        settingsValidator.validate( settings, problems );

        return settings;
    }
