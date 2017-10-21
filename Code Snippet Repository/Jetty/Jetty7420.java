    @Test
    public void test8_1() throws Exception
    {
        StringBuffer req1 = new StringBuffer();
        req1.append("GET /tests/R1.txt HTTP/1.1\n");
        req1.append("Host: localhost\n");
        req1.append("Connection: close\n");
        req1.append("\n");

        HttpTester.Response response = http.request(req1);

        assertEquals("8.1 Persistent Connections", HttpStatus.OK_200, response.getStatus());
        assertTrue("8.1 Persistent Connections", response.get("Content-Length") != null);
        assertThat("8.1 Persistent Connections",response.getContent(),Matchers.containsString("Resource=R1"));

        StringBuffer req2 = new StringBuffer();
        req2.append("GET /tests/R1.txt HTTP/1.1\n");
        req2.append("Host: localhost\n");
        req2.append("\n");

        req2.append("GET /tests/R2.txt HTTP/1.1\n");
        req2.append("Host: localhost\n");
        req2.append("Connection: close\n");
        req2.append("\n");

        req2.append("GET /tests/R3.txt HTTP/1.1\n");
        req2.append("Host: localhost\n");
        req2.append("Connection: close\n");
        req2.append("\n");

        List<HttpTester.Response> responses = http.requests(req2);
        Assert.assertEquals("Response Count",2,responses.size()); // Should not have a R3 response.

        response = responses.get(0); // response 1
        assertEquals("8.1 Persistent Connections", HttpStatus.OK_200, response.getStatus());
        assertTrue("8.1 Persistent Connections",response.get("Content-Length") != null);
        assertTrue("8.1 Peristent Connections", response.getContent().contains("Resource=R1"));

        response = responses.get(1); // response 2
        assertEquals("8.1.2.2 Persistent Connections / Pipeline", HttpStatus.OK_200, response.getStatus());
        assertTrue("8.1.2.2 Persistent Connections / Pipeline", response.get("Content-Length") != null);
        assertEquals("8.1.2.2 Persistent Connections / Pipeline","close", response.get("Connection"));
        assertTrue("8.1.2.2 Peristent Connections / Pipeline", response.getContent().contains("Resource=R2"));
    }
