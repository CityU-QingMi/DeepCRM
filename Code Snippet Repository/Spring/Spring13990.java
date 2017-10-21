	@Override
	public void handleRequestInternal(ServerHttpRequest request, ServerHttpResponse response,
			WebSocketHandler wsHandler, AbstractHttpSockJsSession sockJsSession) throws SockJsException {

		super.handleRequestInternal(request, response, wsHandler, sockJsSession);
		try {
			response.getBody().write("ok".getBytes(StandardCharsets.UTF_8));
		}
		catch (IOException ex) {
			throw new SockJsException("Failed to write to the response body", sockJsSession.getId(), ex);
		}
	}
