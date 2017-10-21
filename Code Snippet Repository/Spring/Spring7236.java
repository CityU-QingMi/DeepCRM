		public SessionSubscriptionInfo addSubscription(String sessionId, String subscriptionId,
				String destination, @Nullable Expression selectorExpression) {

			SessionSubscriptionInfo info = this.sessions.get(sessionId);
			if (info == null) {
				info = new SessionSubscriptionInfo(sessionId);
				SessionSubscriptionInfo value = this.sessions.putIfAbsent(sessionId, info);
				if (value != null) {
					info = value;
				}
			}
			info.addSubscription(destination, subscriptionId, selectorExpression);
			return info;
		}
