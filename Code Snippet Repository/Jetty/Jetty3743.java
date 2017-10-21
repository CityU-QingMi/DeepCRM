    @Test
    public void testThreadName() throws IOException
    {
        HttpURLConnection http = (HttpURLConnection) serverURI.resolve("/foo/bar?a=b").toURL().openConnection();
        assertThat("Response Code", http.getResponseCode(), is(200));
        
        String log = capturedLog.toString(StandardCharsets.UTF_8.name());
        String expectedThreadName = String.format("//%s:%s/foo/bar?a=b",serverURI.getHost(),serverURI.getPort());
        assertThat("ThreadName", log, containsString(expectedThreadName));
        // Look for bad/mangled/duplicated schemes
        assertThat("ThreadName", log, not(containsString("http:"+expectedThreadName)));
        assertThat("ThreadName", log, not(containsString("https:"+expectedThreadName)));
    }
