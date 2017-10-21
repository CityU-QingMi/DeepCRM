	@Nullable
	private String selectProtocol(HttpHeaders headers, WebSocketHandler handler) {
		String protocolHeader = headers.getFirst(SEC_WEBSOCKET_PROTOCOL);
		if (protocolHeader != null) {
			List<String> supportedProtocols = handler.getSubProtocols();
			for (String protocol : StringUtils.commaDelimitedListToStringArray(protocolHeader)) {
				if (supportedProtocols.contains(protocol)) {
					return protocol;
				}
			}
		}
		return null;
	}
