    public void testShouldInterpolateSourceDirectoryReferencedFromResourceDirectoryCorrectly()
        throws Exception
    {
        Model model = new Model();

        Build build = new Build();
        build.setSourceDirectory( "correct" );

        Resource res = new Resource();
        res.setDirectory( "${project.build.sourceDirectory}" );

        build.addResource( res );

        Resource res2 = new Resource();
        res2.setDirectory( "${pom.build.sourceDirectory}" );

        build.addResource( res2 );

        Resource res3 = new Resource();
        res3.setDirectory( "${build.sourceDirectory}" );

        build.addResource( res3 );

        model.setBuild( build );

        ModelInterpolator interpolator = createInterpolator();

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        Model out = interpolator.interpolateModel( model, null, createModelBuildingRequest( context ), collector );
        assertCollectorState( 0, 0, 2, collector );


        List<Resource> outResources = out.getBuild().getResources();
        Iterator<Resource> resIt = outResources.iterator();

        assertEquals( build.getSourceDirectory(), resIt.next().getDirectory() );
        assertEquals( build.getSourceDirectory(), resIt.next().getDirectory() );
        assertEquals( build.getSourceDirectory(), resIt.next().getDirectory() );
    }
