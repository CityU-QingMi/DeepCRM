		public Set<SimpSubscription> findSubscriptions(SimpSubscriptionMatcher matcher) {
			Set<SimpSubscription> result = new HashSet<>();
			for (TransferSimpUser user : this.users.values()) {
				for (TransferSimpSession session : user.sessions) {
					for (SimpSubscription subscription : session.subscriptions) {
						if (matcher.match(subscription)) {
							result.add(subscription);
						}
					}
				}
			}
			return result;
		}
