    @Test
    public void testLFOnlyRequest() throws Exception
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

        String content = "--XyXyXy\n"+
        "Content-Disposition: form-data; name=\"fileName\"\n"+
        "Content-Type: text/plain; charset=US-ASCII\n"+
        "Content-Transfer-Encoding: 8bit\n"+
        "\n"+
        "abc\n"+
        "--XyXyXy\n"+
        "Content-Disposition: form-data; name=\"desc\"\n"+ 
        "Content-Type: text/plain; charset=US-ASCII\n"+ 
        "Content-Transfer-Encoding: 8bit\n"+
        "\n"+
        "123\n"+ 
        "--XyXyXy\n"+ 
        "Content-Disposition: form-data; name=\"title\"\n"+
        "Content-Type: text/plain; charset=US-ASCII\n"+
        "Content-Transfer-Encoding: 8bit\n"+ 
        "\n"+
        "ttt\n"+ 
        "--XyXyXy\n"+
        "Content-Disposition: form-data; name=\"fileup\"; filename=\"test.upload\"\n"+
        "Content-Type: application/octet-stream\n"+
        "Content-Transfer-Encoding: binary\n"+ 
        "\n"+
        "000\n"+ 
        "--XyXyXy--\n";
        request.setContent(content);

        response = HttpTester.parseResponse(tester.getResponses(request.generate()));
        assertEquals(HttpServletResponse.SC_OK,response.getStatus()); 
    }
