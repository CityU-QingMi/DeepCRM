    @Test
    public void test14_23_IncompleteHostHeader() throws Exception
    {
        // HTTP/1.1 - Incomplete (empty) Host header
        try (StacklessLogging stackless = new StacklessLogging(HttpParser.class))
        {
            StringBuffer req4 = new StringBuffer();
            req4.append("GET /tests/R1.txt HTTP/1.1\n");
            req4.append("Host:\n");
            req4.append("Connection: close\n");
            req4.append("\n");

            HttpTester.Response response = http.request(req4);
            assertEquals("14.23 HTTP/1.1 - Empty Host", HttpStatus.OK_200, response.getStatus());
        }
    }
