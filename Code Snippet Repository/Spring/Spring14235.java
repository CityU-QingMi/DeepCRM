	@Test
	 public void handleTransportRequestJsonp() throws Exception {
		TransportHandlingSockJsService jsonpService = new TransportHandlingSockJsService(this.taskScheduler, this.jsonpHandler, this.jsonpSendHandler);
		String sockJsPath = sessionUrlPrefix+ "jsonp";
		setRequest("GET", sockJsPrefix + sockJsPath);
		jsonpService.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);
		assertEquals(404, this.servletResponse.getStatus());

		resetRequestAndResponse();
		jsonpService.setAllowedOrigins(Collections.singletonList("http://mydomain1.com"));
		setRequest("GET", sockJsPrefix + sockJsPath);
		jsonpService.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);
		assertEquals(404, this.servletResponse.getStatus());

		resetRequestAndResponse();
		jsonpService.setAllowedOrigins(Collections.singletonList("*"));
		setRequest("GET", sockJsPrefix + sockJsPath);
		jsonpService.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);
		assertNotEquals(404, this.servletResponse.getStatus());
	}
