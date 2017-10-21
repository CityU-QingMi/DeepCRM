	@Override
	public void handleRequestInternal(ServerHttpRequest request, ServerHttpResponse response,
			AbstractHttpSockJsSession sockJsSession) throws SockJsException {

		String callback = getCallbackParam(request);
		if (!StringUtils.hasText(callback)) {
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
			try {
				response.getBody().write("\"callback\" parameter required".getBytes(StandardCharsets.UTF_8));
			}
			catch (IOException ex) {
				sockJsSession.tryCloseWithSockJsTransportError(ex, CloseStatus.SERVER_ERROR);
				throw new SockJsTransportFailureException("Failed to write to response", sockJsSession.getId(), ex);
			}
			return;
		}

		super.handleRequestInternal(request, response, sockJsSession);
	}
