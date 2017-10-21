    @Test
    public void testAccessSecured404() throws Exception
    {
        URL url = serverHttpsUri.resolve("/nothing/here").toURL();
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setInstanceFollowRedirects(false);
        connection.setAllowUserInteraction(false);
        assertThat("response code",connection.getResponseCode(),is(404));
        connection.disconnect();
    }
