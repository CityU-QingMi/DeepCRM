	@Test
	@SuppressWarnings("")
	public void sockJs() {
		loadBeanDefinitions("websocket-config-handlers-sockjs.xml");

		SimpleUrlHandlerMapping handlerMapping = this.appContext.getBean(SimpleUrlHandlerMapping.class);
		assertNotNull(handlerMapping);

		SockJsHttpRequestHandler testHandler = (SockJsHttpRequestHandler) handlerMapping.getUrlMap().get("/test/**");
		assertNotNull(testHandler);
		unwrapAndCheckDecoratedHandlerType(testHandler.getWebSocketHandler(), TestWebSocketHandler.class);
		SockJsService testSockJsService = testHandler.getSockJsService();

		SockJsHttpRequestHandler fooHandler = (SockJsHttpRequestHandler) handlerMapping.getUrlMap().get("/foo/**");
		assertNotNull(fooHandler);
		unwrapAndCheckDecoratedHandlerType(fooHandler.getWebSocketHandler(), FooWebSocketHandler.class);
		SockJsService sockJsService = fooHandler.getSockJsService();
		assertNotNull(sockJsService);

		assertSame(testSockJsService, sockJsService);

		assertThat(sockJsService, instanceOf(DefaultSockJsService.class));
		DefaultSockJsService defaultSockJsService = (DefaultSockJsService) sockJsService;
		assertThat(defaultSockJsService.getTaskScheduler(), instanceOf(ThreadPoolTaskScheduler.class));
		assertFalse(defaultSockJsService.shouldSuppressCors());

		Map<TransportType, TransportHandler> transportHandlers = defaultSockJsService.getTransportHandlers();
		assertThat(transportHandlers.values(),
				containsInAnyOrder(
						instanceOf(XhrPollingTransportHandler.class),
						instanceOf(XhrReceivingTransportHandler.class),
						instanceOf(JsonpPollingTransportHandler.class),
						instanceOf(JsonpReceivingTransportHandler.class),
						instanceOf(XhrStreamingTransportHandler.class),
						instanceOf(EventSourceTransportHandler.class),
						instanceOf(HtmlFileTransportHandler.class),
						instanceOf(WebSocketTransportHandler.class)));

		WebSocketTransportHandler handler = (WebSocketTransportHandler) transportHandlers.get(TransportType.WEBSOCKET);
		assertEquals(TestHandshakeHandler.class, handler.getHandshakeHandler().getClass());

		List<HandshakeInterceptor> interceptors = defaultSockJsService.getHandshakeInterceptors();
		assertThat(interceptors, contains(instanceOf(FooTestInterceptor.class), instanceOf(BarTestInterceptor.class), instanceOf(OriginHandshakeInterceptor.class)));
	}
