    public void testScmInfoCalculatedCorrectlyOnParentAndChildRead()
        throws Exception
    {
        File localRepo = getLocalRepositoryPath();

        File pom0 = new File( localRepo, "p0/pom.xml" );
        File pom0Basedir = pom0.getParentFile();
        File pom1 = new File( pom0Basedir, "modules/p1/pom.xml" );

        // load the child project, which inherits from p0...
        MavenProject project0 = getProject( pom0 );
        MavenProject project1 = getProject( pom1 );

        System.out.println( "\n\n" );
        System.out.println( "Parent SCM URL is: " + project0.getScm().getUrl() );
        System.out.println( "Child SCM URL is: " + project1.getScm().getUrl() );
        System.out.println();
        System.out.println( "Parent SCM connection is: " + project0.getScm().getConnection() );
        System.out.println( "Child SCM connection is: " + project1.getScm().getConnection() );
        System.out.println();
        System.out.println( "Parent SCM developer connection is: "
                            + project0.getScm().getDeveloperConnection() );
        System.out.println( "Child SCM developer connection is: "
                            + project1.getScm().getDeveloperConnection() );

        assertEquals( project1.getScm().getUrl(), project0.getScm().getUrl() + "/modules/p1" );
        assertEquals( project1.getScm().getConnection(), project0.getScm().getConnection()
                                                         + "/modules/p1" );
        assertEquals( project1.getScm().getDeveloperConnection(), project0.getScm()
                                                                          .getDeveloperConnection()
                                                                  + "/modules/p1" );
    }
