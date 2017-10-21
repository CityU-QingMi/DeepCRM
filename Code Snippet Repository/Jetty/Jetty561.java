    @Test
    public void testCopyRequest() throws Exception
    {
        startClient();

        assertCopyRequest(client.newRequest("http://example.com/some/url")
                .method(HttpMethod.HEAD)
                .version(HttpVersion.HTTP_2)
                .content(new StringContentProvider("some string"))
                .timeout(321, TimeUnit.SECONDS)
                .idleTimeout(2221, TimeUnit.SECONDS)
                .followRedirects(true)
                .header(HttpHeader.CONTENT_TYPE, "application/json")
                .header("X-Some-Custom-Header", "some-value"));

        assertCopyRequest(client.newRequest("https://example.com")
                .method(HttpMethod.POST)
                .version(HttpVersion.HTTP_1_0)
                .content(new StringContentProvider("some other string"))
                .timeout(123231, TimeUnit.SECONDS)
                .idleTimeout(232342, TimeUnit.SECONDS)
                .followRedirects(false)
                .header(HttpHeader.ACCEPT, "application/json")
                .header("X-Some-Other-Custom-Header", "some-other-value"));

        assertCopyRequest(client.newRequest("https://example.com")
                .header(HttpHeader.ACCEPT, "application/json")
                .header(HttpHeader.ACCEPT, "application/xml")
                .header("x-same-name", "value1")
                .header("x-same-name", "value2"));

        assertCopyRequest(client.newRequest("https://example.com")
                .header(HttpHeader.ACCEPT, "application/json")
                .header(HttpHeader.CONTENT_TYPE, "application/json"));

        assertCopyRequest(client.newRequest("https://example.com")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json"));

        assertCopyRequest(client.newRequest("https://example.com")
                .header("X-Custom-Header-1", "value1")
                .header("X-Custom-Header-2", "value2"));

        assertCopyRequest(client.newRequest("https://example.com")
                .header("X-Custom-Header-1", "value")
                .header("X-Custom-Header-2", "value"));
    }
