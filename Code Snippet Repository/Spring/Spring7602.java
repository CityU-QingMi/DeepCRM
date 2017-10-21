		public static MessageExchangeBuilder connect(String sessionId) {
			StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.CONNECT);
			headers.setSessionId(sessionId);
			headers.setAcceptVersion("1.1,1.2");
			headers.setHeartbeat(0, 0);
			Message<?> message = MessageBuilder.createMessage(new byte[0], headers.getMessageHeaders());

			MessageExchangeBuilder builder = new MessageExchangeBuilder(message);
			builder.expected.add(new StompConnectedFrameMessageMatcher(sessionId));
			return builder;
		}
