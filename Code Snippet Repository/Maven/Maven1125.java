    public void testMissingPOMPropertyRefInLargerExpression()
        throws Exception
    {
        String expr = "/path/to/someproject-${baseVersion}";

        MavenProject project = new MavenProject( new Model() );

        ExpressionEvaluator ee = createExpressionEvaluator( project, null, new Properties() );

        Object value = ee.evaluate( expr );

        assertEquals( expr, value );
    }
