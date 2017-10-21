    public Settings buildSettings( MavenExecutionRequest request )
        throws IOException, XmlPullParserException
    {
        SettingsBuildingRequest settingsRequest = new DefaultSettingsBuildingRequest();
        settingsRequest.setUserSettingsFile( request.getUserSettingsFile() );
        settingsRequest.setGlobalSettingsFile( request.getGlobalSettingsFile() );
        settingsRequest.setUserProperties( request.getUserProperties() );
        settingsRequest.setSystemProperties( request.getSystemProperties() );

        return build( settingsRequest );
    }
