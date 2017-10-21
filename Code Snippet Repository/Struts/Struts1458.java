    public void testZipFile() throws MalformedURLException {
        URL url = new URL("zip:/c:/somefile.zip!/");
        URL outputURL = fileManager.normalizeToFileProtocol(url);

        assertNotNull(outputURL);
        assertEquals("file:/c:/somefile.zip", outputURL.toExternalForm());

        url = new URL("zip:/c:/somefile.zip!/somestuf/bla/bla");
        outputURL = fileManager.normalizeToFileProtocol(url);
        assertEquals("file:/c:/somefile.zip", outputURL.toExternalForm());

        url = new URL("zip:c:/somefile.zip!/somestuf/bla/bla");
        outputURL = fileManager.normalizeToFileProtocol(url);
        assertEquals("file:c:/somefile.zip", outputURL.toExternalForm());
    }
