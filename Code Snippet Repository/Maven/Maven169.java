    public Model interpolate( Model model,
                              File projectDir,
                              ProjectBuilderConfiguration config,
                              boolean debugEnabled )
        throws ModelInterpolationException
    {
        StringWriter sWriter = new StringWriter( 1024 );

        MavenXpp3Writer writer = new MavenXpp3Writer();
        try
        {
            writer.write( sWriter, model );
        }
        catch ( IOException e )
        {
            throw new ModelInterpolationException( "Cannot serialize project model for interpolation.", e );
        }

        String serializedModel = sWriter.toString();
        serializedModel = interpolate( serializedModel, model, projectDir, config, debugEnabled );

        StringReader sReader = new StringReader( serializedModel );

        MavenXpp3Reader modelReader = new MavenXpp3Reader();
        try
        {
            model = modelReader.read( sReader );
        }
        catch ( IOException | XmlPullParserException e )
        {
            throw new ModelInterpolationException(
                "Cannot read project model from interpolating filter of serialized version.", e );
        }

        return model;
    }
