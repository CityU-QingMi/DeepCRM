    @Test
    @Ignore
    public void testCatchTaglib() throws IOException
    {
        HttpURLConnection http = (HttpURLConnection) baseUri.resolve("/catch-taglib.jsp").toURL().openConnection();
        assertThat("http response", http.getResponseCode(), is(200));
        try(InputStream input = http.getInputStream())
        {
            String resp = IO.toString(input, StandardCharsets.UTF_8);
            assertThat("Response should be JSP processed", resp, not(containsString("<c:catch>")));
            assertThat("Response should be JSP processed", resp, not(containsString("<jtest:errorhandler>")));
            assertThat("Response", resp, not(containsString("[jtest:errorhandler] exception is null")));
        }
    }
