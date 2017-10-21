    public void testPercentEncodedUrlsMustNotBeDecoded()
        throws Exception
    {
        PomTestWrapper pom = this.buildPom( "url-no-decoding" );
        assertEquals( "http://maven.apache.org/spacy%20path", pom.getValue( "url" ) );
        assertEquals( "http://svn.apache.org/viewvc/spacy%20path", pom.getValue( "scm/url" ) );
        assertEquals( "scm:svn:svn+ssh://svn.apache.org/spacy%20path", pom.getValue( "scm/connection" ) );
        assertEquals( "scm:svn:svn+ssh://svn.apache.org/spacy%20path", pom.getValue( "scm/developerConnection" ) );
        assertEquals( "http://issues.apache.org/spacy%20path", pom.getValue( "issueManagement/url" ) );
        assertEquals( "http://ci.apache.org/spacy%20path", pom.getValue( "ciManagement/url" ) );
        assertEquals( "scm:svn:svn+ssh://dist.apache.org/spacy%20path",
                      pom.getValue( "distributionManagement/repository/url" ) );
        assertEquals( "scm:svn:svn+ssh://snap.apache.org/spacy%20path",
                      pom.getValue( "distributionManagement/snapshotRepository/url" ) );
        assertEquals( "scm:svn:svn+ssh://site.apache.org/spacy%20path",
                      pom.getValue( "distributionManagement/site/url" ) );
    }
