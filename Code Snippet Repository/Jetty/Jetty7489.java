    @Test
    public void testBasic() throws Exception
    {
        URI serverURI = new URI("http://localhost:" + String.valueOf(_httpPort) + "/jmx-webapp/");
        HttpURLConnection http = (HttpURLConnection)serverURI.resolve("ping").toURL().openConnection();
        try (InputStream inputStream = http.getInputStream())
        {
            assertThat("http response", http.getResponseCode(), is(200));
            String resp = IO.toString(inputStream);
            assertThat(resp, startsWith("Servlet Pong at "));
        }
    }
