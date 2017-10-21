	private Message<?> createMessage(String destination, Map<String, Object> headers) {
		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create();
		accessor.setSessionId("session1");
		accessor.setSessionAttributes(new HashMap<>());
		accessor.setDestination(destination);
		if (headers != null) {
			for (Map.Entry<String, Object> entry : headers.entrySet()) {
				accessor.setHeader(entry.getKey(), entry.getValue());
			}
		}
		return MessageBuilder.withPayload(new byte[0]).setHeaders(accessor).build();
	}
