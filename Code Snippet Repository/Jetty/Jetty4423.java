    @Test
    public void testLeadingWhitespaceBodyWithCRLF()
    throws Exception
    {
        String boundary = "AaB03x";

        String body = "              \n\n\n\r\n\r\n\r\n\r\n"+
        "--AaB03x\r\n"+
        "content-disposition: form-data; name=\"field1\"\r\n"+
        "\r\n"+
        "Joe Blow\r\n"+
        "--AaB03x\r\n"+
        "Content-Disposition: form-data; name=\"fileup\"; filename=\"test.upload\"\r\n"+
        "Content-Type: application/octet-stream\r\n"+
        "\r\n" +
        "aaaa,bbbbb"+"\r\n" +
        "--AaB03x--\r\n";

        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;
        request.setMethod("POST");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setURI("/context/dump");
        request.setHeader("Content-Type","multipart/form-data; boundary="+boundary);
        request.setContent(body);

        response = HttpTester.parseResponse(tester.getResponses(request.generate()));
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        assertTrue(response.getContent().contains("aaaa,bbbbb"));
    }
