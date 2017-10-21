    @Override
    public Settings read( File input, Map<String, ?> options )
        throws IOException
    {
        Validate.notNull( input, "input cannot be null" );

        Settings settings = read( ReaderFactory.newXmlReader( input ), options );

        return settings;
    }
