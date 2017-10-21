    @Override
    public Model read( File input, Map<String, ?> options )
        throws IOException
    {
        Validate.notNull( input, "input cannot be null" );

        Model model = read( new FileInputStream( input ), options );

        model.setPomFile( input );

        return model;
    }
