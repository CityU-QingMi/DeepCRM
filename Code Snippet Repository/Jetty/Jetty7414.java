    @Test
    public void test5_2_VirtualHostInsensitive() throws Exception
    {
        // Virtual Host case insensitive

        StringBuffer req3 = new StringBuffer();
        req3.append("GET /tests/ HTTP/1.1\n");
        req3.append("Host: ViRtUalhOst\n"); // mixed case host
        req3.append("Connection: close\n");
        req3.append("\n");

        HttpTester.Response response = http.request(req3);

        assertEquals("5.2 Virtual Host (mixed case)", HttpStatus.OK_200, response.getStatus());
        assertThat("5.2 Virtual Host (mixed case)",response.getContent(),Matchers.containsString("VirtualHost DOCRoot"));
    }
