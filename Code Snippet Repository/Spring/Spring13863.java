	private StompHeaderAccessor afterStompSessionConnected(Message<?> message, StompHeaderAccessor accessor,
			WebSocketSession session) {

		Principal principal = getUser(session);
		if (principal != null) {
			accessor = toMutableAccessor(accessor, message);
			accessor.setNativeHeader(CONNECTED_USER_HEADER, principal.getName());
		}

		long[] heartbeat = accessor.getHeartbeat();
		if (heartbeat[1] > 0) {
			session = WebSocketSessionDecorator.unwrap(session);
			if (session instanceof SockJsSession) {
				((SockJsSession) session).disableHeartbeat();
			}
		}

		return accessor;
	}
