    public void testPluginArtifactIdExpressionReference()
        throws Exception
    {
        MojoExecution exec = newMojoExecution();

        MavenSession session = newMavenSession();

        Object result = new PluginParameterExpressionEvaluator( session, exec ).evaluate( "${plugin.artifactId}" );

        System.out.println( "Result: " + result );

        assertSame( "${plugin.artifactId} expression does not return plugin descriptor's artifactId.",
                    exec.getMojoDescriptor().getPluginDescriptor().getArtifactId(),
                    result );
    }
