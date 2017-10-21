    @Test
    public void testWithCharSet()
    throws Exception
    {
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;
        tester.addServlet(TestServletCharSet.class,"/test3");
        
        // test GET
        request.setMethod("POST");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setURI("/context/test3");
        
        String boundary="XyXyXy";
        request.setHeader("Content-Type","multipart/form-data; boundary="+boundary);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        baos.write(("--" + boundary + "\r\n"+
                "Content-Disposition: form-data; name=\"ttt\"; filename=\"ttt.txt\"\r\n"+
                "Content-Type: application/octet-stream; charset=UTF-8\r\n\r\n").getBytes());
        baos.write("ttt\u01FCzzz".getBytes(StandardCharsets.UTF_8));
        baos.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
  
        
        request.setContent(baos.toByteArray());   

        response = HttpTester.parseResponse(tester.getResponses(request.generate()));
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        assertEquals("ttt\u01FCzzz",response.getContent());
        
    }
