	@Override
	protected void addSubscriptionInternal(
			String sessionId, String subsId, String destination, Message<?> message) {

		Expression expression = null;
		MessageHeaders headers = message.getHeaders();
		String selector = SimpMessageHeaderAccessor.getFirstNativeHeader(getSelectorHeaderName(), headers);
		if (selector != null) {
			try {
				expression = this.expressionParser.parseExpression(selector);
				this.selectorHeaderInUse = true;
				if (logger.isTraceEnabled()) {
					logger.trace("Subscription selector: [" + selector + "]");
				}
			}
			catch (Throwable ex) {
				if (logger.isDebugEnabled()) {
					logger.debug("Failed to parse selector: " + selector, ex);
				}
			}
		}
		this.subscriptionRegistry.addSubscription(sessionId, subsId, destination, expression);
		this.destinationCache.updateAfterNewSubscription(destination, sessionId, subsId);
	}
