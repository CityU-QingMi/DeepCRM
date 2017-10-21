    public static List<Option> jspDependencies()
    {
        List<Option> res = new ArrayList<Option>();
  
        //jetty jsp bundles  
        res.add(mavenBundle().groupId("org.eclipse.jetty.toolchain").artifactId("jetty-schemas").versionAsInProject());
        res.add(mavenBundle().groupId("org.eclipse.jetty.orbit").artifactId("javax.servlet.jsp.jstl").versionAsInProject());
        res.add(mavenBundle().groupId("org.mortbay.jasper").artifactId("apache-el").versionAsInProject());
        res.add(mavenBundle().groupId("org.mortbay.jasper").artifactId("apache-jsp").versionAsInProject());
        res.add(mavenBundle().groupId("org.eclipse.jetty").artifactId("apache-jsp").versionAsInProject());
        res.add(mavenBundle().groupId("org.glassfish.web").artifactId("javax.servlet.jsp.jstl").versionAsInProject());
        res.add(mavenBundle().groupId("org.eclipse.jdt.core.compiler").artifactId("ecj").versionAsInProject());
        res.add(mavenBundle().groupId("org.eclipse.jetty.osgi").artifactId("jetty-osgi-boot-jsp").versionAsInProject().noStart());
        return res;
    }
