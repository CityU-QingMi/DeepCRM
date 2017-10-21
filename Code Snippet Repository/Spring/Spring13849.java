	public Set<SimpSubscription> findSubscriptions(SimpSubscriptionMatcher matcher) {
		Set<SimpSubscription> result = new HashSet<>();
		for (LocalSimpSession session : this.sessions.values()) {
			for (SimpSubscription subscription : session.subscriptions.values()) {
				if (matcher.match(subscription)) {
					result.add(subscription);
				}
			}
		}
		return result;
	}
