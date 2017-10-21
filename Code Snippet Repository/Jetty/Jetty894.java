    @Test
    @Ignore("")
    public void testExternalFastCGIServer() throws Exception
    {
        // Assume a FastCGI server is listening on localhost:9000

        HttpClient client = new HttpClient(new HttpClientTransportOverFCGI("/var/www/php-fcgi"), null);
        client.start();

        ContentResponse response = client.newRequest("localhost", 9000)
                .path("/index.php")
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());

        Path responseFile = Paths.get(System.getProperty("java.io.tmpdir"), "fcgi_response.html");
        Files.write(responseFile, response.getContent(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }
