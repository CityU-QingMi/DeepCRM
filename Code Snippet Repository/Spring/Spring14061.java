	@Test
	public void handlerMapping() {
		SimpleUrlHandlerMapping hm = (SimpleUrlHandlerMapping) this.endpointRegistry.getHandlerMapping();
		assertEquals(0, hm.getUrlMap().size());

		UrlPathHelper pathHelper = new UrlPathHelper();
		this.endpointRegistry.setUrlPathHelper(pathHelper);
		this.endpointRegistry.addEndpoint("/stompOverWebSocket");
		this.endpointRegistry.addEndpoint("/stompOverSockJS").withSockJS();

		//SPR-12403
		assertEquals(1, this.webSocketHandler.getProtocolHandlers().size());

		hm = (SimpleUrlHandlerMapping) this.endpointRegistry.getHandlerMapping();
		assertEquals(2, hm.getUrlMap().size());
		assertNotNull(hm.getUrlMap().get("/stompOverWebSocket"));
		assertNotNull(hm.getUrlMap().get("/stompOverSockJS/**"));
		assertSame(pathHelper, hm.getUrlPathHelper());
	}
