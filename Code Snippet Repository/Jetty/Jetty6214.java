    @Test
    public void testScan_InvalidSignature() throws DeploymentException
    {
        AnnotatedClientEndpointMetadata metadata = new AnnotatedClientEndpointMetadata(container,pojo);
        AnnotatedEndpointScanner<ClientEndpoint, ClientEndpointConfig> scanner = new AnnotatedEndpointScanner<>(metadata);
        try
        {
            scanner.scan();
            Assert.fail("Expected " + InvalidSignatureException.class + " with message that references " + expectedAnnoClass + " annotation");
        }
        catch (InvalidSignatureException e)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("{}:{}",e.getClass(),e.getMessage());
            Assert.assertThat("Message",e.getMessage(),containsString(expectedAnnoClass.getSimpleName()));
        }
    }
