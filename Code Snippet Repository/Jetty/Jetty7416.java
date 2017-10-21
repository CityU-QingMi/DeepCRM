    @Test
    public void test5_2_BadVirtualHost() throws Exception
    {
        // Bad Virtual Host

        StringBuffer req5 = new StringBuffer();
        req5.append("GET /tests/ HTTP/1.1\n");
        req5.append("Host: bad.eclipse.org\n"); // Bad virtual host
        req5.append("Connection: close\n");
        req5.append("\n");

        HttpTester.Response response = http.request(req5);

        assertEquals("5.2 Bad Host",HttpStatus.OK_200, response.getStatus());
        assertThat("5.2 Bad Host",response.getContent(),Matchers.containsString("Default DOCRoot")); // served by default context
    }
