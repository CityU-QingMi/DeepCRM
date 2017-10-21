    @Test
    public void testScan_InvalidSignature() throws DeploymentException
    {
        WebSocketContainerScope container = new SimpleContainerScope(WebSocketPolicy.newClientPolicy());
        AnnotatedServerEndpointMetadata metadata = new AnnotatedServerEndpointMetadata(container,pojo,null);
        AnnotatedEndpointScanner<ServerEndpoint,ServerEndpointConfig> scanner = new AnnotatedEndpointScanner<>(metadata);

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
