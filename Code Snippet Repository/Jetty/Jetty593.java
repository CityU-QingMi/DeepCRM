    @Test
    public void testIDNRedirect() throws Exception
    {
        // Internationalized Domain Name.
        // String exampleHost = scheme + "://пример.рф";
        String exampleHost = scheme + "://\uD0BF\uD180\uD0B8\uD0BC\uD0B5\uD180.\uD180\uD184";
        String incorrectlyDecoded = new String(exampleHost.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

        // Simple server that only parses clear-text HTTP/1.1.
        IDNRedirectServer server = new IDNRedirectServer(exampleHost);
        server.start();

        try
        {
            startClient();

            ContentResponse response = client.newRequest("localhost", server.getLocalPort())
                    .timeout(5, TimeUnit.SECONDS)
                    .followRedirects(false)
                    .send();

            HttpField location = response.getHeaders().getField(HttpHeader.LOCATION);
            Assert.assertEquals(incorrectlyDecoded, location.getValue());

            expectedException.expect(ExecutionException.class);
            expectedException.expectCause(instanceOf(IllegalArgumentException.class));
            client.newRequest("localhost", server.getLocalPort())
                    .timeout(5, TimeUnit.SECONDS)
                    .followRedirects(true)
                    .send();
        }
        finally
        {
            server.stop();
        }
    }
