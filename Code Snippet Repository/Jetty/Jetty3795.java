    @Test
    public void testRedirectSecuredRoot() throws Exception
    {
        URL url = serverHttpsUri.resolve("/").toURL();
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setInstanceFollowRedirects(false);
        connection.setAllowUserInteraction(false);
        assertThat("response code",connection.getResponseCode(),is(200));
        String content = getContent(connection);
        assertThat("response content",content,containsString("<a href=\"/test1\">"));
        connection.disconnect();
    }
