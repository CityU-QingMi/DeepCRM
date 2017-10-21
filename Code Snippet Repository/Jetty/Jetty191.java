    @Test
    public void testRequestParamServletDefault() throws Exception
    {
        HttpURLConnection http = (HttpURLConnection) serverHttpURI.resolve("req-info").toURL().openConnection();
        assertThat("response code", http.getResponseCode(), is(200));
        try(InputStream inputStream = http.getInputStream())
        {
            String resp = IO.toString(inputStream);
            assertThat("Response", resp, containsString("request is PRESENT"));
            assertThat("Response", resp, containsString("parameters.size = [0]"));
        }
    }
