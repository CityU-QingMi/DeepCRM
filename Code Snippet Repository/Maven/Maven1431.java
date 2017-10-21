    @Override
    public void write( Writer output, Map<String, Object> options, Model model )
        throws IOException
    {
        Validate.notNull( output, "output cannot be null" );
        Validate.notNull( model, "model cannot be null" );

        try ( final Writer out = output )
        {
            new MavenXpp3Writer().write( out, model );
        }
    }
