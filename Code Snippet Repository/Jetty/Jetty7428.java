    @Test
    @Ignore("")
    public void test9_8() throws Exception
    {

        StringBuffer req1 = new StringBuffer();
        req1.append("TRACE /rfc2616-webapp/httpmethods HTTP/1.1\n");
        req1.append("Host: localhost\n");
        req1.append("Connection: close\n");
        req1.append("\n");

        HttpTester.Response response = http.request(req1);

        assertEquals("9.8 TRACE / Response Code", HttpStatus.OK_200, response.getStatus());
        assertEquals("9.8 TRACE / Content Type", "message/http", response.get("Content-Type"));
        assertTrue("9.8 TRACE / echo", response.getContent().contains("TRACE /rfc2616-webapp/httpmethods HTTP/1.1"));
        assertTrue("9.8 TRACE / echo", response.getContent().contains("Host: localhost"));
    }
