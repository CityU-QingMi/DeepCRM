    @Override
    public Model read( InputStream input, Map<String, ?> options )
        throws IOException
    {
        Validate.notNull( input, "input cannot be null" );

        try ( final XmlStreamReader in = ReaderFactory.newXmlReader( input ) )
        {
            return read( in, isStrict( options ), getSource( options ) );
        }
    }
