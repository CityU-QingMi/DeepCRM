    @Test
    public void testWithDirectory() throws Exception
    {
        //test that a dir is served by the default servlet
        String request = "" +
                "GET /context/dir HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n";
        String response = _tester.getResponses(request);
        assertTrue(response.contains("This.Is.The.Default."));
    }
