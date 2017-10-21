    @Override
    public void write( Writer output, Map<String, Object> options, Settings settings )
        throws IOException
    {
        Validate.notNull( output, "output cannot be null" );
        Validate.notNull( settings, "settings cannot be null" );

        try ( final Writer out = output )
        {
            new SettingsXpp3Writer().write( out, settings );
        }
    }
