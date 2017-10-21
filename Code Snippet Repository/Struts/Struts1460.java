    public void disabled_testVsFile() throws MalformedURLException {
        URL url = new URL("vfsfile:/c:/somefile.jar!/");
        URL outputURL = fileManager.normalizeToFileProtocol(url);

        assertNotNull(outputURL);
        assertEquals("file:/c:/somefile.jar", outputURL.toExternalForm());

        url = new URL("vfsfile:/c:/somefile.jar!/somestuf/bla/bla");
        outputURL = fileManager.normalizeToFileProtocol(url);
        assertEquals("file:/c:/somefile.jar", outputURL.toExternalForm());

        url = new URL("vfsfile:c:/somefile.jar!/somestuf/bla/bla");
        outputURL = fileManager.normalizeToFileProtocol(url);
        assertEquals("file:c:/somefile.jar", outputURL.toExternalForm());

        url = new URL("vfszip:/c:/somefile.war/somelibrary.jar");
        outputURL = fileManager.normalizeToFileProtocol(url);
        assertEquals("file:/c:/somefile.war/somelibrary.jar", outputURL.toExternalForm());
    }
