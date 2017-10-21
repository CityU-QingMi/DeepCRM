	@Override
	public final void handleRequest(ServerHttpRequest request, ServerHttpResponse response,
			WebSocketHandler wsHandler, SockJsSession wsSession) throws SockJsException {

		AbstractHttpSockJsSession sockJsSession = (AbstractHttpSockJsSession) wsSession;

		// https://github.com/sockjs/sockjs-client/issues/130
		// sockJsSession.setAcceptedProtocol(protocol);

		// Set content type before writing
		response.getHeaders().setContentType(getContentType());

		handleRequestInternal(request, response, sockJsSession);
	}
