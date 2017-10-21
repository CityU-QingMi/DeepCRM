	@Override
	protected Map<String, Object> processHeadersToSend(@Nullable Map<String, Object> headers) {
		if (headers == null) {
			SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
			initHeaders(headerAccessor);
			headerAccessor.setLeaveMutable(true);
			return headerAccessor.getMessageHeaders();
		}
		if (headers.containsKey(NativeMessageHeaderAccessor.NATIVE_HEADERS)) {
			return headers;
		}
		if (headers instanceof MessageHeaders) {
			SimpMessageHeaderAccessor accessor =
					MessageHeaderAccessor.getAccessor((MessageHeaders) headers, SimpMessageHeaderAccessor.class);
			if (accessor != null) {
				return headers;
			}
		}

		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		initHeaders(headerAccessor);
		for (Map.Entry<String, Object> headerEntry : headers.entrySet()) {
			Object value = headerEntry.getValue();
			headerAccessor.setNativeHeader(headerEntry.getKey(), (value != null ? value.toString() : null));
		}
		return headerAccessor.getMessageHeaders();
	}
