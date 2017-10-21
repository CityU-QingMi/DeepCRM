	@Nullable
	private StompSubProtocolHandler initStompSubProtocolHandler() {
		if (this.webSocketHandler == null) {
			return null;
		}
		for (SubProtocolHandler handler : this.webSocketHandler.getProtocolHandlers()) {
			if (handler instanceof StompSubProtocolHandler) {
				return (StompSubProtocolHandler) handler;
			}
		}
		SubProtocolHandler defaultHandler = this.webSocketHandler.getDefaultProtocolHandler();
		if (defaultHandler != null && defaultHandler instanceof StompSubProtocolHandler) {
			return (StompSubProtocolHandler) defaultHandler;
		}
		return null;
	}
