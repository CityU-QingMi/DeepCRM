		private void sendSystemSubscriptions() {
			int i = 0;
			for (String destination : getSystemSubscriptions().keySet()) {
				StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.SUBSCRIBE);
				accessor.setSubscriptionId(String.valueOf(i++));
				accessor.setDestination(destination);
				if (logger.isDebugEnabled()) {
					logger.debug("Subscribing to " + destination + " on \"system\" connection.");
				}
				TcpConnection<byte[]> conn = getTcpConnection();
				if (conn != null) {
					MessageHeaders headers = accessor.getMessageHeaders();
					conn.send(MessageBuilder.createMessage(EMPTY_PAYLOAD, headers)).addCallback(
							result -> {},
							ex -> {
								String error = "Failed to subscribe in \"system\" session.";
								handleTcpConnectionFailure(error, ex);
							});
				}
			}
		}
