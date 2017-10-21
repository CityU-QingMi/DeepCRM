    private String getClassPath()
    {
        StringBuilder cp = new StringBuilder();
        String pathSep = System.getProperty("path.separator");
        cp.append(MavenTestingUtils.getProjectDir("target/classes"));
        cp.append(pathSep);
        cp.append(MavenTestingUtils.getProjectDir("target/test-classes"));
        cp.append(pathSep);
        cp.append(MavenTestingUtils.getProjectDir("../jetty-util/target/classes")); // TODO horrible hack!
        return cp.toString();
    }
