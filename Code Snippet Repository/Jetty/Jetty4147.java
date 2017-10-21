    @Test
    public void testInvoker() throws Exception
    {
        String requestPath = "/servlet/"+TestServlet.class.getName();
        String request =  "GET "+requestPath+" HTTP/1.0\r\n"+
            "Host: tester\r\n"+
            "\r\n";

        String expectedResponse = "HTTP/1.1 200 OK\r\n" +
            "Content-Length: 20\r\n" +
            "\r\n" +
            "Invoked TestServlet!";

        String response = _connector.getResponse(request);
        assertEquals(expectedResponse, response);
    }
