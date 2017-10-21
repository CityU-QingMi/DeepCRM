    @Test
    public void test10_3_RedirectHttp11Path() throws Exception
    {
        // HTTP/1.1

        StringBuffer req2 = new StringBuffer();
        req2.append("GET /redirect/ HTTP/1.1\n");
        req2.append("Host: localhost\n");
        req2.append("\n");

        req2.append("GET /redirect/ HTTP/1.1\n");
        req2.append("Host: localhost\n");
        req2.append("Connection: close\n");
        req2.append("\n");

        List<HttpTester.Response> responses = http.requests(req2);
        Assert.assertEquals("Response Count",2,responses.size());

        HttpTester.Response response = responses.get(0);
        String specId = "10.3 Redirection HTTP/1.1 - basic (response 1)";
        assertEquals(specId,HttpStatus.FOUND_302, response.getStatus());
        assertEquals(specId,server.getScheme() + "://localhost/tests/", response.get("Location"));

        response = responses.get(1);
        specId = "10.3 Redirection HTTP/1.1 - basic (response 2)";
        assertEquals(specId,HttpStatus.FOUND_302, response.getStatus());
        assertEquals(specId,server.getScheme() + "://localhost/tests/", response.get("Location"));
        assertEquals(specId,"close", response.get("Connection"));
    }
