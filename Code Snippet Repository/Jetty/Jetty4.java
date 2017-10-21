    @Test
    public void testWithJsp() throws Exception
    {
        //test that an ordinary jsp is served by jsp servlet
        String request = "" +
                "GET /context/foo.jsp HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n";
        String response = _tester.getResponses(request);
        assertTrue(!response.contains("This.Is.The.Default."));
    }
