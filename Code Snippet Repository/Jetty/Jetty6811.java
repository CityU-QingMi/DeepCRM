    @Test
    public void testAnnotatedBadDuplicateFrameSocket()
    {
        JettyAnnotatedScanner impl = new JettyAnnotatedScanner();
        try
        {
            // Should toss exception
            impl.scan(BadDuplicateFrameSocket.class);
            Assert.fail("Should have thrown " + InvalidWebSocketException.class);
        }
        catch (InvalidWebSocketException e)
        {
            // Validate that we have clear error message to the developer
            Assert.assertThat(e.getMessage(),containsString("Duplicate @OnWebSocketFrame"));
        }
    }
