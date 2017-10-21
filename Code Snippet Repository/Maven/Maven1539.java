    public void testShouldInterpolateOrganizationNameCorrectly()
        throws Exception
    {
        String orgName = "MyCo";

        Model model = new Model();
        model.setName( "${pom.organization.name} Tools" );

        Organization org = new Organization();
        org.setName( orgName );

        model.setOrganization( org );

        ModelInterpolator interpolator = createInterpolator();

        Model out =
            interpolator.interpolateModel( model, new File( "." ), createModelBuildingRequest( context ),
                                           new SimpleProblemCollector() );

        assertEquals( orgName + " Tools", out.getName() );
    }
