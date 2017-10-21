    @Test
    public void testScan_Basic() throws Exception
    {
        AnnotatedClientEndpointMetadata metadata = new AnnotatedClientEndpointMetadata(container,testcase.pojo);
        AnnotatedEndpointScanner<ClientEndpoint, ClientEndpointConfig> scanner = new AnnotatedEndpointScanner<>(metadata);
        scanner.scan();

        Assert.assertThat("Metadata",metadata,notNullValue());

        JsrCallable cm = (JsrCallable)testcase.metadataField.get(metadata);
        Assert.assertThat(testcase.metadataField.toString(),cm,notNullValue());
        int len = testcase.expectedParameters.length;
        for (int i = 0; i < len; i++)
        {
            Class<?> expectedParam = testcase.expectedParameters[i];
            Class<?> actualParam = cm.getParamTypes()[i];

            Assert.assertTrue("Parameter[" + i + "] - expected:[" + expectedParam + "], actual:[" + actualParam + "]",actualParam.equals(expectedParam));
        }
    }
