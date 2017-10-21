	private MultiValueMap<String, String> filterSubscriptions(
			MultiValueMap<String, String> allMatches, Message<?> message) {

		if (!this.selectorHeaderInUse) {
			return allMatches;
		}
		EvaluationContext context = null;
		MultiValueMap<String, String> result = new LinkedMultiValueMap<>(allMatches.size());
		for (String sessionId : allMatches.keySet()) {
			for (String subId : allMatches.get(sessionId)) {
				SessionSubscriptionInfo info = this.subscriptionRegistry.getSubscriptions(sessionId);
				if (info == null) {
					continue;
				}
				Subscription sub = info.getSubscription(subId);
				if (sub == null) {
					continue;
				}
				Expression expression = sub.getSelectorExpression();
				if (expression == null) {
					result.add(sessionId, subId);
					continue;
				}
				if (context == null) {
					context = new StandardEvaluationContext(message);
					context.getPropertyAccessors().add(new SimpMessageHeaderPropertyAccessor());
				}
				try {
					if (Boolean.TRUE.equals(expression.getValue(context, Boolean.class))) {
						result.add(sessionId, subId);
					}
				}
				catch (SpelEvaluationException ex) {
					if (logger.isDebugEnabled()) {
						logger.debug("Failed to evaluate selector: " + ex.getMessage());
					}
				}
				catch (Throwable ex) {
					logger.debug("Failed to evaluate selector", ex);
				}
			}
		}
		return result;
	}
