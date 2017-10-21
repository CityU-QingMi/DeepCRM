    private static Settings readSettingsFile( File settingsFile )
        throws IOException, XmlPullParserException
    {
        Settings settings = null;

        try ( Reader reader = ReaderFactory.newXmlReader( settingsFile ) )
        {
            SettingsXpp3Reader modelReader = new SettingsXpp3Reader();

            settings = modelReader.read( reader );
        }
        return settings;
    }
