    @Test
    public void testAnnotatedBadSignature_NonVoidReturn()
    {
        JettyAnnotatedScanner impl = new JettyAnnotatedScanner();
        try
        {
            // Should toss exception
            impl.scan(BadBinarySignatureSocket.class);
            Assert.fail("Should have thrown " + InvalidWebSocketException.class);
        }
        catch (InvalidWebSocketException e)
        {
            // Validate that we have clear error message to the developer
            Assert.assertThat(e.getMessage(),containsString("must be void"));
        }
    }
