    @Test
    public void testRfcHashExample()
    {
        // What the client sends in the RFC
        String clientKey = "dGhlIHNhbXBsZSBub25jZQ==";

        // What the server responds with
        String serverAccept = AcceptHash.hashKey(clientKey);
        String expectedHash = "s3pPLMBiTxaQ9kYGzzhZRbK+xOo=";

        Assert.assertThat(serverAccept,is(expectedHash));
    }
