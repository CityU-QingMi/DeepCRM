    public static List<Option> annotationDependencies()
    {
        List<Option> res = new ArrayList<Option>();
        res.add(mavenBundle().groupId( "org.eclipse.jetty.orbit" ).artifactId( "javax.mail.glassfish" ).version( "1.4.1.v201005082020" ).noStart());
        res.add(mavenBundle().groupId("org.eclipse.jetty.tests").artifactId("test-container-initializer").versionAsInProject());
        res.add(mavenBundle().groupId("org.eclipse.jetty.tests").artifactId("test-mock-resources").versionAsInProject());
        //test webapp bundle
        res.add(mavenBundle().groupId("org.eclipse.jetty.tests").artifactId("test-spec-webapp").classifier("webbundle").versionAsInProject());
        return res;
    }
