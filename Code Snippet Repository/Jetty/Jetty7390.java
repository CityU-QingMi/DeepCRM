    @Test
    public void testGET_HttpTesting() throws Exception
    {
        HttpTester.Request request = HttpTester.newRequest();
        request.setMethod("GET");
        request.setURI("/tests/alpha.txt");
        request.put("Host","localhost");
        request.put("Connection","close");
        // request.setContent(null);

        HttpTesting testing = new HttpTesting(new HttpSocketImpl(),serverPort);
        HttpTester.Response response = testing.request(request);

        assertEquals(HttpStatus.OK_200, response.getStatus());
        assertEquals("text/plain", response.get("Content-Type"));
        assertTrue(response.getContent().contains("ABCDEFGHIJKLMNOPQRSTUVWXYZ\n"));
    }
