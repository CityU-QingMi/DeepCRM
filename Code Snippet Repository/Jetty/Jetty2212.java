    public static List<Option> testDependencies()
    {
        List<Option> res = new ArrayList<Option>();


        // a bundle that registers a webapp as a service for the jetty osgi core
        // to pick up and deploy
        res.add(mavenBundle().groupId("org.eclipse.jetty.osgi").artifactId("test-jetty-osgi-webapp").versionAsInProject().start());

        
        
        
        //a bundle that registers a new named Server instance
        res.add(mavenBundle().groupId("org.eclipse.jetty.osgi").artifactId("test-jetty-osgi-server").versionAsInProject().start());
       
        return res;
    }
