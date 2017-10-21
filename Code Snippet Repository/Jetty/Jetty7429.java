    @Test
    public void test10_3_RedirectHttp10Path() throws Exception
    {
        String specId;

        String serverURI = server.getServerURI().toASCIIString();

        // HTTP/1.0

        StringBuffer req1 = new StringBuffer();
        req1.append("GET /redirect/ HTTP/1.0\n");
        req1.append("Connection: Close\n");
        req1.append("\n");

        HttpTester.Response response = http.request(req1);

        specId = "10.3 Redirection HTTP/1.0 - basic";
        assertEquals(specId,HttpStatus.FOUND_302, response.getStatus());
        assertEquals(specId,serverURI + "/tests/", response.get("Location"));
    }
