    @Test
    public void testCatchBasic() throws IOException
    {
        HttpURLConnection http = (HttpURLConnection) baseUri.resolve("/catch-basic.jsp").toURL().openConnection();
        assertThat("http response", http.getResponseCode(), is(200));
        try(InputStream input = http.getInputStream())
        {
            String resp = IO.toString(input, StandardCharsets.UTF_8);
            assertThat("Response should be JSP processed", resp, not(containsString("<c:catch")));
            assertThat("Response", resp, containsString("[c:catch] exception : " + JspException.class.getName()));
            assertThat("Response", resp, containsString("[c:catch] exception.message : In &lt;parseNumber&gt;"));
        }
    }
