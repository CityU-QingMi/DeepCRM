    @Test
    public void test10_3_RedirectHttp11Resource() throws Exception
    {
        // HTTP/1.1 - redirect with resource/content

        StringBuffer req4 = new StringBuffer();
        req4.append("GET /redirect/R2.txt HTTP/1.1\n");
        req4.append("Host: localhost\n");
        req4.append("Connection: close\n");
        req4.append("\n");

        HttpTester.Response response = http.request(req4);
        
        String specId = "10.3 Redirection HTTP/1.1 w/content";
        assertThat(specId + " [status]",response.getStatus(),is(HttpStatus.FOUND_302));
        assertThat(specId + " [location]",response.get("Location"),is(server.getScheme() + "://localhost/tests/R2.txt"));
        assertThat(specId + " [connection]",response.get("Connection"),is("close"));
        assertThat(specId + " [content-length]",response.get("Content-Length"), nullValue());
    }
