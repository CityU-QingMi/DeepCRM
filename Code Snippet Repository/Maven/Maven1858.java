    @Override
    public SettingsBuildingResult build( SettingsBuildingRequest request )
        throws SettingsBuildingException
    {
        DefaultSettingsProblemCollector problems = new DefaultSettingsProblemCollector( null );

        Source globalSettingsSource =
            getSettingsSource( request.getGlobalSettingsFile(), request.getGlobalSettingsSource() );
        Settings globalSettings = readSettings( globalSettingsSource, request, problems );

        Source userSettingsSource =
            getSettingsSource( request.getUserSettingsFile(), request.getUserSettingsSource() );
        Settings userSettings = readSettings( userSettingsSource, request, problems );

        settingsMerger.merge( userSettings, globalSettings, TrackableBase.GLOBAL_LEVEL );

        problems.setSource( "" );

        userSettings = interpolate( userSettings, request, problems );

        // for the special case of a drive-relative Windows path, make sure it's absolute to save plugins from trouble
        String localRepository = userSettings.getLocalRepository();
        if ( localRepository != null && localRepository.length() > 0 )
        {
            File file = new File( localRepository );
            if ( !file.isAbsolute() && file.getPath().startsWith( File.separator ) )
            {
                userSettings.setLocalRepository( file.getAbsolutePath() );
            }
        }

        if ( hasErrors( problems.getProblems() ) )
        {
            throw new SettingsBuildingException( problems.getProblems() );
        }

        return new DefaultSettingsBuildingResult( userSettings, problems.getProblems() );
    }
