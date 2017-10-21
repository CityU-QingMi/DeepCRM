	@Test
	@SuppressWarnings("")
	public void webSocketHandlersAttributes() {
		loadBeanDefinitions("websocket-config-handlers-attributes.xml");

		HandlerMapping handlerMapping = this.appContext.getBean(HandlerMapping.class);
		assertNotNull(handlerMapping);
		assertTrue(handlerMapping instanceof SimpleUrlHandlerMapping);

		SimpleUrlHandlerMapping urlHandlerMapping = (SimpleUrlHandlerMapping) handlerMapping;
		assertEquals(2, urlHandlerMapping.getOrder());

		WebSocketHttpRequestHandler handler = (WebSocketHttpRequestHandler) urlHandlerMapping.getUrlMap().get("/foo");
		assertNotNull(handler);
		unwrapAndCheckDecoratedHandlerType(handler.getWebSocketHandler(), FooWebSocketHandler.class);
		HandshakeHandler handshakeHandler = handler.getHandshakeHandler();
		assertNotNull(handshakeHandler);
		assertTrue(handshakeHandler instanceof TestHandshakeHandler);
		List<HandshakeInterceptor> interceptors = handler.getHandshakeInterceptors();
		assertThat(interceptors, contains(instanceOf(FooTestInterceptor.class),
				instanceOf(BarTestInterceptor.class), instanceOf(OriginHandshakeInterceptor.class)));

		handler = (WebSocketHttpRequestHandler) urlHandlerMapping.getUrlMap().get("/test");
		assertNotNull(handler);
		unwrapAndCheckDecoratedHandlerType(handler.getWebSocketHandler(), TestWebSocketHandler.class);
		handshakeHandler = handler.getHandshakeHandler();
		assertNotNull(handshakeHandler);
		assertTrue(handshakeHandler instanceof TestHandshakeHandler);
		interceptors = handler.getHandshakeInterceptors();
		assertThat(interceptors, contains(instanceOf(FooTestInterceptor.class),
				instanceOf(BarTestInterceptor.class), instanceOf(OriginHandshakeInterceptor.class)));
	}
