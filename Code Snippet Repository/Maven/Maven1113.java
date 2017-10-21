    public void testTwoExpressions()
        throws Exception
    {
        Build build = new Build();
        build.setDirectory( "expected-directory" );
        build.setFinalName( "expected-finalName" );

        Model model = new Model();
        model.setBuild( build );

        ExpressionEvaluator expressionEvaluator =
            createExpressionEvaluator( new MavenProject( model ), null, new Properties() );

        Object value = expressionEvaluator.evaluate( "${project.build.directory}" + FS + "${project.build.finalName}" );

        assertEquals( "expected-directory" + File.separatorChar + "expected-finalName", value );
    }
