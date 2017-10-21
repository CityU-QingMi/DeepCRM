    public void testPluginConfigurationCreation()
        throws Exception
    {
        File pom = getProject( "project-with-additional-lifecycle-elements" );
        MavenSession session = createMavenSession( pom );
        MojoDescriptor mojoDescriptor =
            mojoDescriptorCreator.getMojoDescriptor( "org.apache.maven.its.plugins:maven-it-plugin:0.1:java", session,
                                                     session.getCurrentProject() );
        Xpp3Dom dom = MojoDescriptorCreator.convert( mojoDescriptor );
        System.out.println( dom );
    }
