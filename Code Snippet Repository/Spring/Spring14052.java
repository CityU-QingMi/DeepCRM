	@Test
	@SuppressWarnings("")
	public void sockJsAttributes() {
		loadBeanDefinitions("websocket-config-handlers-sockjs-attributes.xml");

		SimpleUrlHandlerMapping handlerMapping = appContext.getBean(SimpleUrlHandlerMapping.class);
		assertNotNull(handlerMapping);

		SockJsHttpRequestHandler handler = (SockJsHttpRequestHandler) handlerMapping.getUrlMap().get("/test/**");
		assertNotNull(handler);
		unwrapAndCheckDecoratedHandlerType(handler.getWebSocketHandler(), TestWebSocketHandler.class);

		SockJsService sockJsService = handler.getSockJsService();
		assertNotNull(sockJsService);
		assertThat(sockJsService, instanceOf(TransportHandlingSockJsService.class));
		TransportHandlingSockJsService transportService = (TransportHandlingSockJsService) sockJsService;
		assertThat(transportService.getTaskScheduler(), instanceOf(TestTaskScheduler.class));
		assertThat(transportService.getTransportHandlers().values(),
				containsInAnyOrder(
						instanceOf(XhrPollingTransportHandler.class),
						instanceOf(XhrStreamingTransportHandler.class)));

		assertEquals("testSockJsService", transportService.getName());
		assertFalse(transportService.isWebSocketEnabled());
		assertFalse(transportService.isSessionCookieNeeded());
		assertEquals(2048, transportService.getStreamBytesLimit());
		assertEquals(256, transportService.getDisconnectDelay());
		assertEquals(1024, transportService.getHttpMessageCacheSize());
		assertEquals(20, transportService.getHeartbeatTime());
		assertEquals("/js/sockjs.min.js", transportService.getSockJsClientLibraryUrl());
		assertEquals(TestMessageCodec.class, transportService.getMessageCodec().getClass());

		List<HandshakeInterceptor> interceptors = transportService.getHandshakeInterceptors();
		assertThat(interceptors, contains(instanceOf(OriginHandshakeInterceptor.class)));
		assertTrue(transportService.shouldSuppressCors());
		assertTrue(transportService.getAllowedOrigins().contains("http://mydomain1.com"));
		assertTrue(transportService.getAllowedOrigins().contains("http://mydomain2.com"));
	}
