    public void testValidGlobalSettings()
        throws Exception
    {
        String basedir = System.getProperty( "basedir", System.getProperty( "user.dir" ) );

        File globalSettingsFile = new File( basedir, "src/conf/settings.xml" );
        assertTrue( globalSettingsFile.getAbsolutePath(), globalSettingsFile.isFile() );

        try ( Reader reader = new InputStreamReader( new FileInputStream( globalSettingsFile ), "UTF-8" ) )
        {
            new SettingsXpp3Reader().read( reader );
        }
    }
