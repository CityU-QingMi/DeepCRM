    @Test
    public void testNoBoundary() throws Exception
    {
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;

        tester.setAttribute("fileName", "abc");
        tester.setAttribute("desc", "123");
        tester.setAttribute("title", "ttt");
        request.setMethod("POST");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setURI("/context/dump");

        request.setHeader("Content-Type","multipart/form-data");

        // generated and parsed test
        String content = "--\r\n"+
        "Content-Disposition: form-data; name=\"fileName\"\r\n"+
        "Content-Type: text/plain; charset=US-ASCII\r\n"+
        "Content-Transfer-Encoding: 8bit\r\n"+
        "\r\n"+
        "abc\r\n"+
        "--\r\n"+
        "Content-Disposition: form-data; name=\"desc\"\r\n"+ 
        "Content-Type: text/plain; charset=US-ASCII\r\n"+ 
        "Content-Transfer-Encoding: 8bit\r\n"+
        "\r\n"+
        "123\r\n"+ 
        "--\r\n"+ 
        "Content-Disposition: form-data; name=\"title\"\r\n"+
        "Content-Type: text/plain; charset=US-ASCII\r\n"+
        "Content-Transfer-Encoding: 8bit\r\n"+ 
        "\r\n"+
        "ttt\r\n"+ 
        "--\r\n"+
        "Content-Disposition: form-data; name=\"fileup\"; filename=\"test.upload\"\r\n"+
        "Content-Type: application/octet-stream\r\n"+
        "Content-Transfer-Encoding: binary\r\n"+ 
        "\r\n"+
        "000\r\n"+ 
        "----\r\n";
        request.setContent(content);

        response = HttpTester.parseResponse(tester.getResponses(request.generate()));
        assertEquals(HttpServletResponse.SC_OK,response.getStatus());
    }
