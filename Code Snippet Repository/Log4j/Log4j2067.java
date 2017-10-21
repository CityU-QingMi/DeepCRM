    private void testExtractPathFromJarUrlNotDecodedIfFileExists(final String existingFile)
            throws MalformedURLException, UnsupportedEncodingException, URISyntaxException {
        URL url = ResolverUtilTest.class.getResource(existingFile);
        if (!url.getProtocol().equals("jar")) {
            // create fake jar: URL that resolves to existing file
            url = new URL("jar:" + url.toExternalForm() + "!/some/entry");
        }
        final String actual = new ResolverUtil().extractPath(url);
        assertTrue("should not be decoded: " + actual, actual.endsWith(existingFile));
    }
