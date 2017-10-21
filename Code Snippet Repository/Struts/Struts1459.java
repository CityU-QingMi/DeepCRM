    public void testWSJarFile() throws MalformedURLException {
        URL url = new URL("wsjar:file:/c:/somefile.jar!/");
        URL outputURL = fileManager.normalizeToFileProtocol(url);

        assertNotNull(outputURL);
        assertEquals("file:/c:/somefile.jar", outputURL.toExternalForm());

        url = new URL("wsjar:file:/c:/somefile.jar!/somestuf/bla/bla");
        outputURL = fileManager.normalizeToFileProtocol(url);
        assertEquals("file:/c:/somefile.jar", outputURL.toExternalForm());

        url = new URL("wsjar:file:c:/somefile.jar!/somestuf/bla/bla");
        outputURL = fileManager.normalizeToFileProtocol(url);
        assertEquals("file:c:/somefile.jar", outputURL.toExternalForm());
    }
