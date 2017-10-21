    @Test
    public void test10_3_RedirectHttp10Resource() throws Exception
    {
        // HTTP/1.0 - redirect with resource/content

        StringBuffer req3 = new StringBuffer();
        req3.append("GET /redirect/R1.txt HTTP/1.0\n");
        req3.append("Host: localhost\n");
        req3.append("Connection: close\n");
        req3.append("\n");

        HttpTester.Response response = http.request(req3);

        String specId = "10.3 Redirection HTTP/1.0 w/content";
        assertEquals(specId,HttpStatus.FOUND_302, response.getStatus());
        assertEquals(specId,server.getScheme() + "://localhost/tests/R1.txt", response.get("Location"));
    }
