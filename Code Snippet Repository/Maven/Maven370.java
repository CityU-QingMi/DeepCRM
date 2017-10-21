    public void testProjectInheritance()
        throws Exception
    {
        // ----------------------------------------------------------------------
        // Check p0 value for org name
        // ----------------------------------------------------------------------

        MavenProject p0 = getProject( projectFile( "maven.t01", "p0" ) );

        assertEquals( "p0-org", p0.getOrganization().getName() );

        // ----------------------------------------------------------------------
        // Check p1 value for org name
        // ----------------------------------------------------------------------

        MavenProject p1 = getProject( projectFile( "maven.t01", "p1" ) );

        assertEquals( "p1-org", p1.getOrganization().getName() );

        // ----------------------------------------------------------------------
        // Check p2 value for org name
        // ----------------------------------------------------------------------

        MavenProject p2 = getProject( projectFile( "maven.t01", "p2" ) );

        assertEquals( "p2-org", p2.getOrganization().getName() );

        // ----------------------------------------------------------------------
        // Check p2 value for org name
        // ----------------------------------------------------------------------

        MavenProject p3 = getProject( projectFile( "maven.t01", "p3" ) );

        assertEquals( "p3-org", p3.getOrganization().getName() );

        // ----------------------------------------------------------------------
        // Check p4 value for org name
        // ----------------------------------------------------------------------

        MavenProject p4 = getProject( projectFile( "maven.t01", "p4" ) );

        assertEquals( "p4-org", p4.getOrganization().getName() );
    }
