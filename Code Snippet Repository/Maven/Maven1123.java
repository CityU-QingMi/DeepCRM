    public void testEscapedVariablePassthroughInLargerExpression()
        throws Exception
    {
        String var = "${var}";
        String key = var + " with version: ${project.version}";

        Model model = new Model();
        model.setVersion( "1" );

        MavenProject project = new MavenProject( model );

        ExpressionEvaluator ee = createExpressionEvaluator( project, null, new Properties() );

        Object value = ee.evaluate( "$" + key );

        assertEquals( "${var} with version: 1", value );
    }
