    public void testShouldExtractPluginArtifacts()
        throws Exception
    {
        PluginDescriptor pd = new PluginDescriptor();

        Artifact artifact = createArtifact( "testGroup", "testArtifact", "1.0" );

        pd.setArtifacts( Collections.singletonList( artifact ) );

        ExpressionEvaluator ee = createExpressionEvaluator( createDefaultProject(), pd, new Properties() );

        Object value = ee.evaluate( "${plugin.artifacts}" );

        assertTrue( value instanceof List );

        @SuppressWarnings( "unchecked" )
        List<Artifact> artifacts = (List<Artifact>) value;

        assertEquals( 1, artifacts.size() );

        Artifact result = artifacts.get( 0 );

        assertEquals( "testGroup", result.getGroupId() );
    }
