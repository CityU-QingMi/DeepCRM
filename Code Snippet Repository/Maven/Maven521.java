    public Metadata read( InputStream input, Map<String, ?> options )
        throws IOException
    {
        Validate.notNull( input, "input cannot be null" );

        try ( final InputStream in = input )
        {
            return new MetadataXpp3Reader().read( in, isStrict( options ) );
        }
        catch ( XmlPullParserException e )
        {
            throw new MetadataParseException( e.getMessage(), e.getLineNumber(), e.getColumnNumber(), e );
        }
    }
