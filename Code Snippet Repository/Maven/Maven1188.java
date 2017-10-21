    public void testUrlAppendWithChildPathAdjustment()
        throws Exception
    {
        PomTestWrapper pom = this.buildPom( "url-append/child" );
        assertEquals( "http://project.url/child", pom.getValue( "url" ) );
        assertEquals( "http://viewvc.project.url/child", pom.getValue( "scm/url" ) );
        assertEquals( "http://scm.project.url/child", pom.getValue( "scm/connection" ) );
        assertEquals( "https://scm.project.url/child", pom.getValue( "scm/developerConnection" ) );
        assertEquals( "http://site.project.url/child", pom.getValue( "distributionManagement/site/url" ) );
    }
