    private Settings build( SettingsBuildingRequest request )
        throws IOException, XmlPullParserException
    {
        try
        {
            return settingsBuilder.build( request ).getEffectiveSettings();
        }
        catch ( SettingsBuildingException e )
        {
            throw (IOException) new IOException( e.getMessage() ).initCause( e );
        }
    }
