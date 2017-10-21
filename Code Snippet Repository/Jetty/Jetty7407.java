    @Test
    public void testGetReference() throws Exception
    {
        URI uri = serverURI.resolve(path);

        HttpURLConnection conn = null;
        try
        {
            conn = (HttpURLConnection)uri.toURL().openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            assertResponse(conn);
        }
        finally
        {
            conn.disconnect();
        }
    }
