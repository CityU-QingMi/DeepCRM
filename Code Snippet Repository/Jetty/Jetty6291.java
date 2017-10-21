    private String GET(URI destURI) throws IOException
    {
        HttpURLConnection http = (HttpURLConnection) destURI.toURL().openConnection();
        assertThat("HTTP GET (" + destURI + ") Response Code", http.getResponseCode(), is(200));
        try (InputStream in = http.getInputStream();
             InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
             StringWriter writer = new StringWriter())
        {
            IO.copy(reader, writer);
            return writer.toString();
        }
    }
