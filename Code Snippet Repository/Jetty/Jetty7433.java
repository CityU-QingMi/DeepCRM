    @Test
    public void test14_3_AcceptEncodingGzip() throws Exception
    {
        String specId;

        // Gzip accepted

        StringBuffer req1 = new StringBuffer();
        req1.append("GET /rfc2616-webapp/solutions.html HTTP/1.1\n");
        req1.append("Host: localhost\n");
        req1.append("Accept-Encoding: gzip\n");
        req1.append("Connection: close\n");
        req1.append("\n");

        HttpTester.Response response = http.request(req1);
        specId = "14.3 Accept-Encoding Header";
        assertEquals(specId, HttpStatus.OK_200, response.getStatus());
        assertEquals(specId,"gzip", response.get("Content-Encoding"));
        assertEquals(specId,"text/html", response.get("Content-Type"));
    }
