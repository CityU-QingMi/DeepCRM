    public void testOpenWithJarProtocol() throws IOException {
        FileManager fileManager = new DefaultFileManager();

        URL url = ClassLoaderUtil.getResource("xwork-jar.jar", ClassLoaderUtil.class);
        URL jarUrl = new URL("jar", "", url.toExternalForm() + "!/");
        URL outputURL = fileManager.normalizeToFileProtocol(jarUrl);

        assertNotNull(outputURL);
        assertUrlCanBeOpened(outputURL);
    }
