    public static List<Option> coreJettyDependencies()
    {
        List<Option> res = new ArrayList<Option>();
        
        res.add(mavenBundle().groupId( "org.ow2.asm" ).artifactId( "asm" ).versionAsInProject().start());
        res.add(mavenBundle().groupId( "org.ow2.asm" ).artifactId( "asm-commons" ).versionAsInProject().start());
        res.add(mavenBundle().groupId( "org.ow2.asm" ).artifactId( "asm-tree" ).versionAsInProject().start());
        res.add(mavenBundle().groupId( "org.apache.aries" ).artifactId( "org.apache.aries.util" ).versionAsInProject().start());
        res.add(mavenBundle().groupId( "org.apache.aries.spifly" ).artifactId( "org.apache.aries.spifly.dynamic.bundle" ).versionAsInProject().start());

        res.add(mavenBundle().groupId( "org.eclipse.jetty.toolchain" ).artifactId( "jetty-osgi-servlet-api" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "javax.annotation" ).artifactId( "javax.annotation-api" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.apache.geronimo.specs" ).artifactId( "geronimo-jta_1.1_spec" ).version("1.1.1").noStart());

        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-util" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-deploy" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-server" ).versionAsInProject().noStart());  
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-servlet" ).versionAsInProject().noStart());  
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-http" ).versionAsInProject());
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-xml" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-webapp" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-io" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-continuation" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-security" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-servlets" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-client" ).versionAsInProject().noStart());  
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-jndi" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-plus" ).versionAsInProject());
        res.add(mavenBundle().groupId( "org.eclipse.jetty" ).artifactId( "jetty-annotations" ).versionAsInProject().start());
        res.add(mavenBundle().groupId( "org.eclipse.jetty.websocket" ).artifactId( "websocket-api" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.eclipse.jetty.websocket" ).artifactId( "websocket-common" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.eclipse.jetty.websocket" ).artifactId( "websocket-servlet" ).versionAsInProject());
        res.add(mavenBundle().groupId( "org.eclipse.jetty.websocket" ).artifactId( "websocket-server" ).versionAsInProject());
        res.add(mavenBundle().groupId( "org.eclipse.jetty.websocket" ).artifactId( "websocket-client" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "javax.websocket" ).artifactId( "javax.websocket-api" ).versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.eclipse.jetty.websocket" ).artifactId( "javax-websocket-client-impl").versionAsInProject().noStart());
        res.add(mavenBundle().groupId( "org.eclipse.jetty.websocket" ).artifactId( "javax-websocket-server-impl").versionAsInProject().start());
        res.add(mavenBundle().groupId( "org.eclipse.jetty.osgi" ).artifactId( "jetty-osgi-boot" ).versionAsInProject().start());
        return res;
    }
