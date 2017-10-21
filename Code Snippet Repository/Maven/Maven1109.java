    public void testPOMPropertyExtractionWithMissingProject_WithDotNotation()
        throws Exception
    {
        String key = "m2.name";
        String checkValue = "value";

        Properties properties = new Properties();
        properties.setProperty( key, checkValue );

        Model model = new Model();
        model.setProperties( properties );

        MavenProject project = new MavenProject( model );

        ExpressionEvaluator ee = createExpressionEvaluator( project, null, new Properties() );

        Object value = ee.evaluate( "${" + key + "}" );

        assertEquals( checkValue, value );
    }
