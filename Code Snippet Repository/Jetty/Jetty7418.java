    @Test
    public void test5_2_VirtualHostAbsoluteURI_Http10_WithoutHostHeader() throws Exception
    {
        // Virtual Host as Absolute URI

        StringBuffer req6 = new StringBuffer();
        req6.append("GET http://VirtualHost/tests/ HTTP/1.0\n");
        req6.append("Connection: close\n");
        req6.append("\n");

        HttpTester.Response response = http.request(req6);

        assertEquals("5.2 Virtual Host as AbsoluteURI (No Host Header / HTTP 1.0)",HttpStatus.OK_200, response.getStatus());
        assertThat("5.2 Virtual Host as AbsoluteURI (No Host Header / HTTP 1.1)",response.getContent(),Matchers.containsString("VirtualHost DOCRoot"));
    }
