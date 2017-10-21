    @Test
    public void test5_2_VirtualHostAbsoluteURI_WithHostHeader() throws Exception
    {
        // Virtual Host as Absolute URI (with Host header)

        StringBuffer req7 = new StringBuffer();
        req7.append("GET http://VirtualHost/tests/ HTTP/1.1\n");
        req7.append("Host: localhost\n"); // is ignored (would normally trigger default context)
        req7.append("Connection: close\n");
        req7.append("\n");

        HttpTester.Response response = http.request(req7);

        assertEquals("5.2 Virtual Host as AbsoluteURI (and Host header)", HttpStatus.OK_200, response.getStatus());
        // System.err.println(response.getContent());
        assertThat("5.2 Virtual Host as AbsoluteURI (and Host header)",response.getContent(),Matchers.containsString("VirtualHost DOCRoot"));
    }
