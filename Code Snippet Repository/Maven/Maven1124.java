    public void testMultipleSubExpressionsInLargerExpression()
        throws Exception
    {
        String key = "${project.artifactId} with version: ${project.version}";

        Model model = new Model();
        model.setArtifactId( "test" );
        model.setVersion( "1" );

        MavenProject project = new MavenProject( model );

        ExpressionEvaluator ee = createExpressionEvaluator( project, null, new Properties() );

        Object value = ee.evaluate( key );

        assertEquals( "test with version: 1", value );
    }
