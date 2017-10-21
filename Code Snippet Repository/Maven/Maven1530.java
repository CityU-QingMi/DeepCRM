    public void testEnvars()
        throws Exception
    {
         Properties context = new Properties();

        context.put( "env.HOME", "/path/to/home" );

        Model model = new Model();

        Properties modelProperties = new Properties();

        modelProperties.setProperty( "outputDirectory", "${env.HOME}" );

        model.setProperties( modelProperties );

        ModelInterpolator interpolator = createInterpolator();

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        Model out =
            interpolator.interpolateModel( model, new File( "." ), createModelBuildingRequest( context ), collector );
        assertProblemFree( collector );

        assertEquals( "/path/to/home", out.getProperties().getProperty( "outputDirectory" ) );
    }
