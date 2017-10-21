	private void testJsonpTransport(String callbackValue, boolean expectSuccess) throws Exception {
		JsonpPollingTransportHandler transportHandler = new JsonpPollingTransportHandler();
		transportHandler.initialize(this.sockJsConfig);
		PollingSockJsSession session = transportHandler.createSession("1", this.webSocketHandler, null);

		resetRequestAndResponse();
		setRequest("POST", "/");

		if (callbackValue != null) {
			// need to encode the query parameter
			this.servletRequest.setQueryString("c=" + UriUtils.encodeQueryParam(callbackValue, "UTF-8"));
			this.servletRequest.addParameter("c", callbackValue);
		}

		try {
			transportHandler.handleRequest(this.request, this.response, this.webSocketHandler, session);
		}
		catch (SockJsTransportFailureException ex) {
			if (expectSuccess) {
				throw new AssertionError("Unexpected transport failure", ex);
			}
		}

		if (expectSuccess) {
			assertEquals(200, this.servletResponse.getStatus());
			assertEquals("application/javascript;charset=UTF-8", this.response.getHeaders().getContentType().toString());
			verify(this.webSocketHandler).afterConnectionEstablished(session);
		}
		else {
			assertEquals(500, this.servletResponse.getStatus());
			verifyNoMoreInteractions(this.webSocketHandler);
		}
	}
