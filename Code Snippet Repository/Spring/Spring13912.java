	@Nullable
	protected String selectProtocol(List<String> requestedProtocols, WebSocketHandler webSocketHandler) {
		List<String> handlerProtocols = determineHandlerSupportedProtocols(webSocketHandler);
		for (String protocol : requestedProtocols) {
			if (handlerProtocols.contains(protocol.toLowerCase())) {
				return protocol;
			}
			if (this.supportedProtocols.contains(protocol.toLowerCase())) {
				return protocol;
			}
		}
		return null;
	}
