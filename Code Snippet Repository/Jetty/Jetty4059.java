    @Test
    public void testUnsatisfiableRanges() throws Exception
    {
        String response;
        response = connector.getResponse(
                "GET /context/data.txt HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n"+
                "Range: bytes=100-110\r\n" +
                "\r\n");
        assertResponseContains("416 Range Not Satisfiable", response);
    }
