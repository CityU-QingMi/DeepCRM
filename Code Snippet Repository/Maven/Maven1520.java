    public void testAndConditionInActivation()
            throws Exception
    {
        Properties sysProperties = new Properties();
        sysProperties.setProperty( "myproperty", "test" );

        ModelBuilder builder = new DefaultModelBuilderFactory().newInstance();
        assertNotNull( builder );

        DefaultModelBuildingRequest request = new DefaultModelBuildingRequest();
        request.setProcessPlugins( true );
        request.setPomFile( getPom( "complex" ) );
        request.setSystemProperties( sysProperties );

        ModelBuildingResult result = builder.build( request );
        assertNotNull( result );
        assertNotNull( result.getEffectiveModel() );
        assertEquals( "activated-1", result.getEffectiveModel().getProperties().get( "profile.file" ) );
        assertNull( result.getEffectiveModel().getProperties().get( "profile.miss" ) );
    }
