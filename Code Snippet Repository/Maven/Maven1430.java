    @Override
    public void write( File output, Map<String, Object> options, Model model )
        throws IOException
    {
        Validate.notNull( output, "output cannot be null" );
        Validate.notNull( model, "model cannot be null" );

        output.getParentFile().mkdirs();

        write( WriterFactory.newXmlWriter( output ), options, model );
    }
