		public WebSocketMessage<?> encode(Message<byte[]> message, Class<? extends WebSocketSession> sessionType) {
			StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
			Assert.notNull(accessor, "No StompHeaderAccessor available");
			byte[] payload = message.getPayload();
			byte[] bytes = ENCODER.encode(accessor.getMessageHeaders(), payload);

			boolean useBinary = (payload.length > 0  &&
					!(SockJsSession.class.isAssignableFrom(sessionType)) &&
					MimeTypeUtils.APPLICATION_OCTET_STREAM.isCompatibleWith(accessor.getContentType()));

			return (useBinary ? new BinaryMessage(bytes) : new TextMessage(bytes));
		}
