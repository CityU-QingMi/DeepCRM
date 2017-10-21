    @Test
    public void testCROnlyRequest() throws Exception
    { 
        String boundary="XyXyXy";
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;
        tester.addServlet(BoundaryServlet.class,"/testb");
        tester.setAttribute("fileName", "abc");
        tester.setAttribute("desc", "123");
        tester.setAttribute("title", "ttt");
        request.setMethod("POST");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setURI("/context/testb");
        request.setHeader("Content-Type","multipart/form-data; boundary="+boundary);

        String content = "--XyXyXy\r"+
        "Content-Disposition: form-data; name=\"fileName\"\r"+
        "Content-Type: text/plain; charset=US-ASCII\r"+
        "Content-Transfer-Encoding: 8bit\r"+
        "\r"+
        "abc\r"+
        "--XyXyXy\r"+
        "Content-Disposition: form-data; name=\"desc\"\r"+ 
        "Content-Type: text/plain; charset=US-ASCII\r"+ 
        "Content-Transfer-Encoding: 8bit\r"+
        "\r"+
        "123\r"+ 
        "--XyXyXy\r"+ 
        "Content-Disposition: form-data; name=\"title\"\r"+
        "Content-Type: text/plain; charset=US-ASCII\r"+
        "Content-Transfer-Encoding: 8bit\r"+ 
        "\r"+
        "ttt\r"+ 
        "--XyXyXy\r"+
        "Content-Disposition: form-data; name=\"fileup\"; filename=\"test.upload\"\r"+
        "Content-Type: application/octet-stream\r"+
        "Content-Transfer-Encoding: binary\r"+ 
        "\r"+
        "000\r"+ 
        "--XyXyXy--\r";
        request.setContent(content);

        response = HttpTester.parseResponse(tester.getResponses(request.generate()));
        assertEquals(HttpServletResponse.SC_OK,response.getStatus()); 
    }
