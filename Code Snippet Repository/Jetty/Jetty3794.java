    @Test
    public void testRedirectUnsecuredRoot() throws Exception
    {
        URL url = serverHttpUri.resolve("/").toURL();
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setInstanceFollowRedirects(false);
        connection.setAllowUserInteraction(false);
        assertThat("response code",connection.getResponseCode(),is(302));
        assertThat("location header",connection.getHeaderField("Location"),is(serverHttpsUri.resolve("/").toASCIIString()));
        connection.disconnect();
    }
