    public void testJarFileWithJarWordInsidePath() throws MalformedURLException {
        URL url = new URL("jar:file:/c:/workspace/projar/somefile.jar!/");
        URL outputURL = fileManager.normalizeToFileProtocol(url);

        assertNotNull(outputURL);
        assertEquals("file:/c:/workspace/projar/somefile.jar", outputURL.toExternalForm());

        url = new URL("jar:file:/c:/workspace/projar/somefile.jar!/somestuf/bla/bla");
        outputURL = fileManager.normalizeToFileProtocol(url);
        assertEquals("file:/c:/workspace/projar/somefile.jar", outputURL.toExternalForm());

        url = new URL("jar:file:c:/workspace/projar/somefile.jar!/somestuf/bla/bla");
        outputURL = fileManager.normalizeToFileProtocol(url);
        assertEquals("file:c:/workspace/projar/somefile.jar", outputURL.toExternalForm());
    }
