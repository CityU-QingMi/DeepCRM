    @Test
    public void testScan_Basic() throws Exception
    {
        WebSocketContainerScope container = new SimpleContainerScope(WebSocketPolicy.newClientPolicy());
        AnnotatedServerEndpointMetadata metadata = new AnnotatedServerEndpointMetadata(container,testcase.pojo,null);
        AnnotatedEndpointScanner<ServerEndpoint, ServerEndpointConfig> scanner = new AnnotatedEndpointScanner<>(metadata);
        scanner.scan();

        Assert.assertThat("Metadata",metadata,notNullValue());

        JsrCallable method = (JsrCallable)testcase.metadataField.get(metadata);
        Assert.assertThat(testcase.metadataField.toString(),method,notNullValue());
        int len = testcase.expectedParameters.length;
        for (int i = 0; i < len; i++)
        {
            Class<?> expectedParam = testcase.expectedParameters[i];
            Class<?> actualParam = method.getParamTypes()[i];

            Assert.assertTrue("Parameter[" + i + "] - expected:[" + expectedParam + "], actual:[" + actualParam + "]",actualParam.equals(expectedParam));
        }
    }
