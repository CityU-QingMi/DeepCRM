    public void testCompleteWiring()
        throws Exception
    {
        ModelBuilder builder = new DefaultModelBuilderFactory().newInstance();
        assertNotNull( builder );

        DefaultModelBuildingRequest request = new DefaultModelBuildingRequest();
        request.setProcessPlugins( true );
        request.setPomFile( getPom( "simple" ) );

        ModelBuildingResult result = builder.build( request );
        assertNotNull( result );
        assertNotNull( result.getEffectiveModel() );
        assertEquals( "activated", result.getEffectiveModel().getProperties().get( "profile.file" ) );
        Xpp3Dom conf = (Xpp3Dom) result.getEffectiveModel().getBuild().getPlugins().get( 0 ).getConfiguration();
        assertEquals( "1.5", conf.getChild( "source" ).getValue() );
        assertEquals( "  1.5  ", conf.getChild( "target" ).getValue() );
    }
