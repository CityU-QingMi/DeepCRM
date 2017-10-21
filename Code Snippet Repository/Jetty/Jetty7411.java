    @Test
    public void testGetReference() throws Exception
    {
        URI uri = serverURI.resolve(path);

        HttpURLConnection conn = null;
        try
        {
            conn = (HttpURLConnection)uri.toURL().openConnection();
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);
            assertResponse(conn);
        }
        finally
        {
            conn.disconnect();
        }
    }
