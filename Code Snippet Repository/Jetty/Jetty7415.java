    @Test
    public void test5_2_NoVirtualHost() throws Exception
    {
        // No Virtual Host

        StringBuffer req4 = new StringBuffer();
        req4.append("GET /tests/ HTTP/1.1\n");
        req4.append("Connection: close\n");
        req4.append("\n"); // no virtual host

        HttpTester.Response response = http.request(req4);
        System.err.println(response);

        assertEquals("5.2 No Host",HttpStatus.BAD_REQUEST_400,response.getStatus());
    }
