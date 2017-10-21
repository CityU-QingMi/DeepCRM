    public void testProjectInheritance()
        throws Exception
    {
        MavenProject p4 = getProject( projectFile( "p4" ) );

        assertEquals( "p4", p4.getName() );

        // ----------------------------------------------------------------------
        // Value inherited from p3
        // ----------------------------------------------------------------------

        assertEquals( "2000", p4.getInceptionYear() );

        // ----------------------------------------------------------------------
        // Value taken from p2
        // ----------------------------------------------------------------------

        assertEquals( "mailing-list", p4.getMailingLists().get( 0 ).getName() );

        // ----------------------------------------------------------------------
        // Value taken from p1
        // ----------------------------------------------------------------------

        assertEquals( "scm-url/p2/p3/p4", p4.getScm().getUrl() );

        // ----------------------------------------------------------------------
        // Value taken from p4
        // ----------------------------------------------------------------------

        assertEquals( "Codehaus", p4.getOrganization().getName() );

        // ----------------------------------------------------------------------
        // Value taken from super model
        // ----------------------------------------------------------------------

        assertEquals( "4.0.0", p4.getModelVersion() );

        assertEquals( "4.0.0", p4.getModelVersion() );
    }
