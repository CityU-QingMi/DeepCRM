    @Override
    public Settings read( InputStream input, Map<String, ?> options )
        throws IOException
    {
        Validate.notNull( input, "input cannot be null" );

        try ( final InputStream in = input )
        {
            return new SettingsXpp3Reader().read( in, isStrict( options ) );
        }
        catch ( XmlPullParserException e )
        {
            throw new SettingsParseException( e.getMessage(), e.getLineNumber(), e.getColumnNumber(), e );
        }
    }
