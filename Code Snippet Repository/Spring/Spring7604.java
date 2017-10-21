		public static MessageExchangeBuilder disconnectWithReceipt(String sessionId, String receiptId) {
			StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.DISCONNECT);
			headers.setSessionId(sessionId);
			headers.setReceipt(receiptId);
			Message<?> message = MessageBuilder.createMessage(new byte[0], headers.getMessageHeaders());

			MessageExchangeBuilder builder = new MessageExchangeBuilder(message);
			builder.expected.add(new StompReceiptFrameMessageMatcher(sessionId, receiptId));
			return builder;
		}
