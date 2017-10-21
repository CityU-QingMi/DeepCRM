    private String getResponse(URI uri) throws IOException
    {
        HttpURLConnection http = (HttpURLConnection)uri.toURL().openConnection();
        assertThat("Valid Response Code",http.getResponseCode(),anyOf(is(200),is(404)));

        try (InputStream in = http.getInputStream())
        {
            return IO.toString(in,StandardCharsets.UTF_8);
        }
    }
