    @Test
    public void testOnCloseCall() throws Exception
    {
        // Scan annotations
        AnnotatedClientEndpointMetadata metadata = new AnnotatedClientEndpointMetadata(container,testcase.closeClass);
        AnnotatedEndpointScanner<ClientEndpoint, ClientEndpointConfig> scanner = new AnnotatedEndpointScanner<>(metadata);
        scanner.scan();

        // Build up EventDriver
        WebSocketPolicy policy = WebSocketPolicy.newClientPolicy();
        ClientEndpointConfig config = metadata.getConfig();
        TrackingSocket endpoint = (TrackingSocket)testcase.closeClass.newInstance();
        EndpointInstance ei = new EndpointInstance(endpoint,config,metadata);
        JsrEvents<ClientEndpoint, ClientEndpointConfig> jsrevents = new JsrEvents<>(metadata);

        EventDriver driver = new JsrAnnotatedEventDriver(policy,ei,jsrevents);

        // Execute onClose call
        driver.onClose(new CloseInfo(StatusCode.NORMAL,"normal"));

        // Test captured event
        EventQueue<String> events = endpoint.eventQueue;
        Assert.assertThat("Number of Events Captured",events.size(),is(1));
        String closeEvent = events.poll();
        Assert.assertThat("Close Event",closeEvent,is(testcase.expectedCloseEvent));
    }
