    public void testNonInheritedElementsInSubtreesOverriddenByChild()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "limited-inheritance/child" );
        assertEquals( null, pom.getValue( "organization/url" ) );
        assertEquals( null, pom.getValue( "issueManagement/system" ) );
        assertEquals( 0, ( (List<?>) pom.getValue( "ciManagement/notifiers" ) ).size() );
        assertEquals( "child-distros", pom.getValue( "distributionManagement/repository/id" ) );
        assertEquals( "ssh://child.url/distros", pom.getValue( "distributionManagement/repository/url" ) );
        assertEquals( null, pom.getValue( "distributionManagement/repository/name" ) );
        assertEquals( true, pom.getValue( "distributionManagement/repository/uniqueVersion" ) );
        assertEquals( "default", pom.getValue( "distributionManagement/repository/layout" ) );
        assertEquals( "child-snaps", pom.getValue( "distributionManagement/snapshotRepository/id" ) );
        assertEquals( "ssh://child.url/snaps", pom.getValue( "distributionManagement/snapshotRepository/url" ) );
        assertEquals( null, pom.getValue( "distributionManagement/snapshotRepository/name" ) );
        assertEquals( true, pom.getValue( "distributionManagement/snapshotRepository/uniqueVersion" ) );
        assertEquals( "default", pom.getValue( "distributionManagement/snapshotRepository/layout" ) );
        assertEquals( "child-site", pom.getValue( "distributionManagement/site/id" ) );
        assertEquals( "scp://child.url/site", pom.getValue( "distributionManagement/site/url" ) );
        assertEquals( null, pom.getValue( "distributionManagement/site/name" ) );
    }
