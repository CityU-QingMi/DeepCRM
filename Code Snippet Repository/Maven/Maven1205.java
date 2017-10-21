    public void testParentPomPackagingMustBePom()
        throws Exception
    {
        try
        {
            buildPom( "parent-pom-packaging/sub" );
            fail( "Wrong packaging of parent POM was not rejected" );
        }
        catch ( ProjectBuildingException e )
        {
            // expected
        }
    }
