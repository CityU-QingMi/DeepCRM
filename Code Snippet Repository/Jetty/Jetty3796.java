    @Test
    public void testAccessUnsecuredHandler() throws Exception
    {
        URL url = serverHttpUri.resolve("/test1").toURL();
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setInstanceFollowRedirects(false);
        connection.setAllowUserInteraction(false);
        assertThat("response code",connection.getResponseCode(),is(302));
        assertThat("location header",connection.getHeaderField("Location"),is(serverHttpsUri.resolve("/test1").toASCIIString()));
        connection.disconnect();
    }
