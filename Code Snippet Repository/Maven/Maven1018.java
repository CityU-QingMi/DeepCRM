    @Override
    public PersistedToolchains read( InputStream input, Map<String, ?> options )
        throws IOException
    {
        Validate.notNull( input, "input cannot be null" );

        try ( final InputStream in = input )
        {
            return new MavenToolchainsXpp3Reader().read( in, isStrict( options ) );
        }
        catch ( XmlPullParserException e )
        {
            throw new ToolchainsParseException( e.getMessage(), e.getLineNumber(), e.getColumnNumber(), e );
        }
    }
