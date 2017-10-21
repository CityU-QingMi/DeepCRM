    @Test
    public void testAccessUnsecured404() throws Exception
    {
        URL url = serverHttpUri.resolve("/nothing/here").toURL();
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setInstanceFollowRedirects(false);
        connection.setAllowUserInteraction(false);
        assertThat("response code",connection.getResponseCode(),is(302));
        assertThat("location header",connection.getHeaderField("Location"),is(serverHttpsUri.resolve("/nothing/here").toASCIIString()));
        connection.disconnect();
    }
