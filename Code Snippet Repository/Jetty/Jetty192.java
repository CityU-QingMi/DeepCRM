    @Test
    public void testRequestParamServletAbc() throws Exception
    {
        HttpURLConnection http = (HttpURLConnection) serverHttpURI.resolve("req-info?abc=123").toURL().openConnection();
        assertThat("response code", http.getResponseCode(), is(200));
        try(InputStream inputStream = http.getInputStream())
        {
            String resp = IO.toString(inputStream);
            assertThat("Response", resp, containsString("request is PRESENT"));
            assertThat("Response", resp, containsString("parameters.size = [1]"));
            assertThat("Response", resp, containsString(" param[abc] = [123]"));
        }
    }
