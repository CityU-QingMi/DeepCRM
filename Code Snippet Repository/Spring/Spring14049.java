	@Test
	public void webSocketHandlers() {
		loadBeanDefinitions("websocket-config-handlers.xml");

		Map<String, HandlerMapping> handlersMap = this.appContext.getBeansOfType(HandlerMapping.class);
		assertNotNull(handlersMap);
		assertThat(handlersMap.values(), hasSize(2));

		for (HandlerMapping hm : handlersMap.values()) {
			assertTrue(hm instanceof SimpleUrlHandlerMapping);
			SimpleUrlHandlerMapping shm = (SimpleUrlHandlerMapping) hm;

			if (shm.getUrlMap().keySet().contains("/foo")) {
				assertThat(shm.getUrlMap().keySet(), contains("/foo", "/bar"));
				WebSocketHttpRequestHandler handler = (WebSocketHttpRequestHandler) shm.getUrlMap().get("/foo");
				assertNotNull(handler);
				unwrapAndCheckDecoratedHandlerType(handler.getWebSocketHandler(), FooWebSocketHandler.class);
				HandshakeHandler handshakeHandler = handler.getHandshakeHandler();
				assertNotNull(handshakeHandler);
				assertTrue(handshakeHandler instanceof DefaultHandshakeHandler);
				assertFalse(handler.getHandshakeInterceptors().isEmpty());
				assertTrue(handler.getHandshakeInterceptors().get(0) instanceof OriginHandshakeInterceptor);
			}
			else {
				assertThat(shm.getUrlMap().keySet(), contains("/test"));
				WebSocketHttpRequestHandler handler = (WebSocketHttpRequestHandler) shm.getUrlMap().get("/test");
				assertNotNull(handler);
				unwrapAndCheckDecoratedHandlerType(handler.getWebSocketHandler(), TestWebSocketHandler.class);
				HandshakeHandler handshakeHandler = handler.getHandshakeHandler();
				assertNotNull(handshakeHandler);
				assertTrue(handshakeHandler instanceof DefaultHandshakeHandler);
				assertFalse(handler.getHandshakeInterceptors().isEmpty());
				assertTrue(handler.getHandshakeInterceptors().get(0) instanceof OriginHandshakeInterceptor);
			}
		}
	}
