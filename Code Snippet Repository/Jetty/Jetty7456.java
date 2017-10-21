    @Test
    public void test5_2_DefaultHost() throws Exception
    {
        // Default Host

        StringBuffer req1 = new StringBuffer();
        req1.append("GET /tests/index.html HTTP/1.1\n");
        req1.append("Host: localhost\n"); // default host
        req1.append("Connection: close\n");
        req1.append("\r\n");

        HttpTester.Response response = http.request(req1);

        assertEquals("5.2 Default Host", HttpStatus.OK_200, response.getStatus());
        assertThat("5.2 Default Host",response.getContent(),Matchers.containsString("Default DOCRoot"));
    }
