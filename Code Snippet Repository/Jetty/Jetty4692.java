    public static void main(String[] args)
    {
        Path buildRoot = MavenTestingUtils.getProjectDir("..").toPath();
        buildRoot = buildRoot.normalize().toAbsolutePath();

        // Test to make sure we are in right directory
        Path rootPomXml = buildRoot.resolve("pom.xml");
        Path distPomXml = buildRoot.resolve("jetty-distribution/pom.xml");
        if (!Files.exists(rootPomXml) || !Files.exists(distPomXml))
        {
            System.err.println("Not build root directory: " + buildRoot);
            System.exit(-1);
        }

        try
        {
            new CorrectMavenCentralRefs().fix(buildRoot);
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
    }
