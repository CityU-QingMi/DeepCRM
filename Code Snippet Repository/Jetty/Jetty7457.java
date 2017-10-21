    @Test
    public void test5_2_VirtualHost() throws Exception
    {
        // Virtual Host

        StringBuffer req2 = new StringBuffer();
        req2.append("GET /tests/ HTTP/1.1\n");
        req2.append("Host: VirtualHost\n"); // simple virtual host
        req2.append("Connection: close\n");
        req2.append("\r\n");

        HttpTester.Response response = http.request(req2);

        assertEquals("5.2 Virtual Host", HttpStatus.OK_200, response.getStatus());
        assertThat("5.2 Virtual Host",response.getContent(),Matchers.containsString("VirtualHost DOCRoot"));
    }
