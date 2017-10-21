	protected void sendMessageToSubscribers(@Nullable String destination, Message<?> message) {
		MultiValueMap<String,String> subscriptions = this.subscriptionRegistry.findSubscriptions(message);
		if (!subscriptions.isEmpty() && logger.isDebugEnabled()) {
			logger.debug("Broadcasting to " + subscriptions.size() + " sessions.");
		}
		long now = System.currentTimeMillis();
		for (Map.Entry<String, List<String>> subscriptionEntry : subscriptions.entrySet()) {
			for (String subscriptionId : subscriptionEntry.getValue()) {
				SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
				initHeaders(headerAccessor);
				headerAccessor.setSessionId(subscriptionEntry.getKey());
				headerAccessor.setSubscriptionId(subscriptionId);
				headerAccessor.copyHeadersIfAbsent(message.getHeaders());
				Object payload = message.getPayload();
				Message<?> reply = MessageBuilder.createMessage(payload, headerAccessor.getMessageHeaders());
				try {
					getClientOutboundChannel().send(reply);
				}
				catch (Throwable ex) {
					if (logger.isErrorEnabled()) {
						logger.error("Failed to send " + message, ex);
					}
				}
				finally {
					SessionInfo info = this.sessions.get(subscriptionEntry.getKey());
					if (info != null) {
						info.setLastWriteTime(now);
					}
				}
			}
		}
	}
