		public static MessageExchangeBuilder subscribeWithReceipt(String sessionId, String subscriptionId,
				String destination, String receiptId) {

			StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SUBSCRIBE);
			headers.setSessionId(sessionId);
			headers.setSubscriptionId(subscriptionId);
			headers.setDestination(destination);
			headers.setReceipt(receiptId);
			Message<?> message = MessageBuilder.createMessage(new byte[0], headers.getMessageHeaders());

			MessageExchangeBuilder builder = new MessageExchangeBuilder(message);
			builder.expected.add(new StompReceiptFrameMessageMatcher(sessionId, receiptId));
			return builder;
		}
