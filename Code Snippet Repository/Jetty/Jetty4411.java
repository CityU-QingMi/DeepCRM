    @Test
    public void testCorrectlyEncodedMSFilename() throws Exception
    {
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;
        // test GET
        request.setMethod("POST");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setURI("/context/dump");
        
        String boundary="XyXyXy";
        request.setHeader("Content-Type","multipart/form-data; boundary="+boundary);
        
        
        String content = "--" + boundary + "\r\n"+
        "Content-Disposition: form-data; name=\"fileup\"; filename=\"c:\\\\this\\\\really\\\\is\\\\some\\\\path\\\\to\\\\a\\\\file.txt\"\r\n"+
        "Content-Type: application/octet-stream\r\n\r\n"+
        "How now brown cow."+
        "\r\n--" + boundary + "--\r\n\r\n";
        
        request.setContent(content);
        
        response = HttpTester.parseResponse(tester.getResponses(request.generate()));
        
        //System.out.printf("Content: [%s]%n", response.getContent());
        assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));        
        assertThat(response.getContent(), containsString("Filename [c:\\this\\really\\is\\some\\path\\to\\a\\file.txt]"));
        assertThat(response.getContent(), containsString("How now brown cow.")); 
    }
