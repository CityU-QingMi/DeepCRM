    @Test
    public void testUrlsBasic() throws IOException
    {
        HttpURLConnection http = (HttpURLConnection) baseUri.resolve("/urls.jsp").toURL().openConnection();
        assertThat("http response", http.getResponseCode(), is(200));
        try(InputStream input = http.getInputStream())
        {
            String resp = IO.toString(input, StandardCharsets.UTF_8);
            assertThat("Response should be JSP processed", resp, not(containsString("<c:url")));
            assertThat("Response", resp, containsString("[c:url value] = /ref.jsp;jsessionid="));
            assertThat("Response", resp, containsString("[c:url param] = ref.jsp;key=value;jsessionid="));
        }
    }
