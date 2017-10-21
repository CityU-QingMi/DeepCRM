    @Override
    public void write( File output, Map<String, Object> options, Settings settings )
        throws IOException
    {
        Validate.notNull( output, "output cannot be null" );
        Validate.notNull( settings, "settings cannot be null" );

        output.getParentFile().mkdirs();

        write( WriterFactory.newXmlWriter( output ), options, settings );
    }
