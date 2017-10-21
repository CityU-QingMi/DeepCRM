    private void checkResourcePathsForExampleWebApp(String root) throws IOException
    {
        File testDirectory = setupTestDirectory();

        ContextHandler handler = new ContextHandler();

        Assert.assertTrue("Not a directory " + testDirectory, testDirectory.isDirectory());
        handler.setBaseResource(Resource.newResource(Resource.toURL(testDirectory)));

        List<String> paths = new ArrayList<>(handler.getResourcePaths(root));
        Assert.assertEquals(2, paths.size());

        Collections.sort(paths);
        Assert.assertEquals("/WEB-INF/jsp/", paths.get(0));
        Assert.assertEquals("/WEB-INF/web.xml", paths.get(1));
    }
