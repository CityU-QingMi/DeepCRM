    public void testAppendArtifactIdOfParentAndChildToInheritedUrls()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "url-inheritance/another-parent/sub" );
        assertEquals( "http://parent.url/ap/child", pom.getValue( "url" ) );
        assertEquals( "http://parent.url/org", pom.getValue( "organization/url" ) );
        assertEquals( "http://parent.url/license.txt", pom.getValue( "licenses[1]/url" ) );
        assertEquals( "http://parent.url/viewvc/ap/child", pom.getValue( "scm/url" ) );
        assertEquals( "http://parent.url/scm/ap/child", pom.getValue( "scm/connection" ) );
        assertEquals( "https://parent.url/scm/ap/child", pom.getValue( "scm/developerConnection" ) );
        assertEquals( "http://parent.url/issues", pom.getValue( "issueManagement/url" ) );
        assertEquals( "http://parent.url/ci", pom.getValue( "ciManagement/url" ) );
        assertEquals( "http://parent.url/dist", pom.getValue( "distributionManagement/repository/url" ) );
        assertEquals( "http://parent.url/snaps", pom.getValue( "distributionManagement/snapshotRepository/url" ) );
        assertEquals( "http://parent.url/site/ap/child", pom.getValue( "distributionManagement/site/url" ) );
        assertEquals( "http://parent.url/download", pom.getValue( "distributionManagement/downloadUrl" ) );
    }
