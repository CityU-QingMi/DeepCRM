    public void testPluginDescriptorExpressionReference()
        throws Exception
    {
        MojoExecution exec = newMojoExecution();

        MavenSession session = newMavenSession();

        Object result = new PluginParameterExpressionEvaluator( session, exec ).evaluate( "${plugin}" );

        System.out.println( "Result: " + result );

        assertSame( "${plugin} expression does not return plugin descriptor.",
                    exec.getMojoDescriptor().getPluginDescriptor(),
                    result );
    }
