    public static void main(String[] args)
    {
        File realDistHome = MavenTestingUtils.getProjectDir("../jetty-distribution/target/distribution");
        File outputDir = MavenTestingUtils.getTestResourceDir("dist-home");
        try
        {
            new RebuildTestResources(realDistHome,outputDir).rebuild();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }
