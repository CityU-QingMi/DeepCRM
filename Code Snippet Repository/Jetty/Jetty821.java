    private static boolean hasJettyGeneratedPath(File basedir, String expectedWarFilename)
    {
        File[] paths = basedir.listFiles();
        if (paths != null)
        {
            for (File path : paths)
            {
                if (path.exists() && path.isDirectory() && path.getName().startsWith("jetty-") && path.getName().contains(expectedWarFilename))
                {
                    System.err.println("Found expected generated directory: " + path);
                    return true;
                }
            }
            System.err.println("did not find "+expectedWarFilename+" in "+Arrays.asList(paths));
        }
        return false;
    }
