		@Nullable
		public String removeSubscription(String subscriptionId) {
			for (Map.Entry<String, Set<DefaultSubscriptionRegistry.Subscription>> destinationEntry :
					this.destinationLookup.entrySet()) {
				Set<Subscription> subs = destinationEntry.getValue();
				if (subs != null) {
					for (Subscription sub : subs) {
						if (sub.getId().equals(subscriptionId) && subs.remove(sub)) {
							synchronized (this.destinationLookup) {
								if (subs.isEmpty()) {
									this.destinationLookup.remove(destinationEntry.getKey());
								}
							}
							return destinationEntry.getKey();
						}
					}
				}
			}
			return null;
		}
