    @Override
    public Model read( Reader input, Map<String, ?> options )
        throws IOException
    {
        Validate.notNull( input, "input cannot be null" );

        try ( final Reader in = input )
        {
            return read( in, isStrict( options ), getSource( options ) );
        }
    }
