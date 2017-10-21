	@Test
	public void handleTransportRequestWebsocket() throws Exception {
		TransportHandlingSockJsService wsService = new TransportHandlingSockJsService(this.taskScheduler, this.wsTransportHandler);
		String sockJsPath = "/websocket";
		setRequest("GET", sockJsPrefix + sockJsPath);
		wsService.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);
		assertNotEquals(403, this.servletResponse.getStatus());

		resetRequestAndResponse();
		List<String> allowed = Collections.singletonList("http://mydomain1.com");
		OriginHandshakeInterceptor interceptor = new OriginHandshakeInterceptor(allowed);
		wsService.setHandshakeInterceptors(Collections.singletonList(interceptor));
		setRequest("GET", sockJsPrefix + sockJsPath);
		this.servletRequest.addHeader(HttpHeaders.ORIGIN, "http://mydomain1.com");
		wsService.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);
		assertNotEquals(403, this.servletResponse.getStatus());

		resetRequestAndResponse();
		setRequest("GET", sockJsPrefix + sockJsPath);
		this.servletRequest.addHeader(HttpHeaders.ORIGIN, "http://mydomain2.com");
		wsService.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);
		assertEquals(403, this.servletResponse.getStatus());
	}
