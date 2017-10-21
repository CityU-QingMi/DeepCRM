    public void testPluginArtifactsExpressionReference()
        throws Exception
    {
        MojoExecution exec = newMojoExecution();

        Artifact depArtifact = createArtifact( "group", "artifact", "1" );

        List<Artifact> deps = new ArrayList<>();
        deps.add( depArtifact );

        exec.getMojoDescriptor().getPluginDescriptor().setArtifacts( deps );

        MavenSession session = newMavenSession();

        @SuppressWarnings( "unchecked" )
        List<Artifact> depResults =
            (List<Artifact>) new PluginParameterExpressionEvaluator( session, exec ).evaluate( "${plugin.artifacts}" );

        System.out.println( "Result: " + depResults );

        assertNotNull( depResults );
        assertEquals( 1, depResults.size() );
        assertSame( "dependency artifact is wrong.", depArtifact, depResults.get( 0 ) );
    }
