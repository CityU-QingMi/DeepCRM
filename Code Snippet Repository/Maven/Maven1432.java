    @Override
    public void write( OutputStream output, Map<String, Object> options, Model model )
        throws IOException
    {
        Validate.notNull( output, "output cannot be null" );
        Validate.notNull( model, "model cannot be null" );

        String encoding = model.getModelEncoding();
        // TODO Use StringUtils here
        if ( encoding == null || encoding.length() <= 0 )
        {
            encoding = "UTF-8";
        }

        try ( final Writer out = new OutputStreamWriter( output, encoding ) )
        {
            write( out, options, model );
        }
    }
