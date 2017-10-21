    @Override
    public PersistedToolchains read( Reader input, Map<String, ?> options )
        throws IOException
    {
        Validate.notNull( input, "input cannot be null" );

        try ( final Reader in = input )
        {
            return new MavenToolchainsXpp3Reader().read( in, isStrict( options ) );
        }
        catch ( XmlPullParserException e )
        {
            throw new ToolchainsParseException( e.getMessage(), e.getLineNumber(), e.getColumnNumber(), e );
        }
    }
