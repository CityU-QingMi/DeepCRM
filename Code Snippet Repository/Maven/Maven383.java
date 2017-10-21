    public void testScmInfoCalculatedCorrectlyOnChildOnlyRead()
        throws Exception
    {
        File localRepo = getLocalRepositoryPath();

        File pom1 = new File( localRepo, "p0/modules/p1/pom.xml" );

        // load the child project, which inherits from p0...
        MavenProject project1 = getProject( pom1 );

        System.out.println( "\n\n" );
        System.out.println( "Child SCM URL is: " + project1.getScm().getUrl() );
        System.out.println( "Child SCM connection is: " + project1.getScm().getConnection() );
        System.out.println( "Child SCM developer connection is: "
                            + project1.getScm().getDeveloperConnection() );

        assertEquals( "http://host/viewer?path=/p0/modules/p1", project1.getScm().getUrl() );
        assertEquals( "scm:svn:http://host/p0/modules/p1", project1.getScm().getConnection() );
        assertEquals( "scm:svn:https://host/p0/modules/p1", project1.getScm().getDeveloperConnection() );
    }
