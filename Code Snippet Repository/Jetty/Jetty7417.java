    @Test
    public void test5_2_VirtualHostAbsoluteURI_Http11_WithoutHostHeader() throws Exception
    {
        // Virtual Host as Absolute URI

        StringBuffer req6 = new StringBuffer();
        req6.append("GET http://VirtualHost/tests/ HTTP/1.1\n");
        req6.append("Connection: close\n");
        req6.append("\n");

        HttpTester.Response response = http.request(req6);

        // No host header should always return a 400 Bad Request by 19.6.1.1
        assertEquals("5.2 Virtual Host as AbsoluteURI (No Host Header / HTTP 1.1)",HttpStatus.BAD_REQUEST_400,response.getStatus());
    }
