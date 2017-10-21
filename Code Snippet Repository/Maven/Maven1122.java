    public void testEscapedVariablePassthrough()
        throws Exception
    {
        String var = "${var}";

        Model model = new Model();
        model.setVersion( "1" );

        MavenProject project = new MavenProject( model );

        ExpressionEvaluator ee = createExpressionEvaluator( project, null, new Properties() );

        Object value = ee.evaluate( "$" + var );

        assertEquals( var, value );
    }
