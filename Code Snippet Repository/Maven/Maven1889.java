    public void testCompleteWiring()
        throws Exception
    {
        SettingsBuilder builder = new DefaultSettingsBuilderFactory().newInstance();
        assertNotNull( builder );

        DefaultSettingsBuildingRequest request = new DefaultSettingsBuildingRequest();
        request.setSystemProperties( System.getProperties() );
        request.setUserSettingsFile( getSettings( "simple" ) );

        SettingsBuildingResult result = builder.build( request );
        assertNotNull( result );
        assertNotNull( result.getEffectiveSettings() );
    }
