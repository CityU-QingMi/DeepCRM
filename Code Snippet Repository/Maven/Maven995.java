    public Settings buildSettings( File userSettingsFile )
        throws IOException, XmlPullParserException
    {
        File globalSettingsFile =
            getFile( "${maven.conf}/settings.xml", "maven.conf",
                     MavenSettingsBuilder.ALT_GLOBAL_SETTINGS_XML_LOCATION );

        SettingsBuildingRequest request = new DefaultSettingsBuildingRequest();
        request.setUserSettingsFile( userSettingsFile );
        request.setGlobalSettingsFile( globalSettingsFile );
        request.setSystemProperties( SystemProperties.getSystemProperties() );
        return build( request );
    }
