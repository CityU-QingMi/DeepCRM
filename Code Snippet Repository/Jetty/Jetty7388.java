    @Test
    public void testGET_URL() throws Exception
    {
        URL url = new URL("http://localhost:" + serverPort + "/tests/alpha.txt");
        URLConnection conn = url.openConnection();
        conn.connect();

        InputStream in = conn.getInputStream();

        String response = IO.toString(in);
        String expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZ\n";
        
        Assert.assertEquals("Response",expected,response);
    }
