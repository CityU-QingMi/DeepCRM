		@Nullable
		public Subscription getSubscription(String subscriptionId) {
			for (Map.Entry<String, Set<DefaultSubscriptionRegistry.Subscription>> destinationEntry : this.destinationLookup.entrySet()) {
				Set<Subscription> subs = destinationEntry.getValue();
				if (subs != null) {
					for (Subscription sub : subs) {
						if (sub.getId().equalsIgnoreCase(subscriptionId)) {
							return sub;
						}
					}
				}
			}
			return null;
		}
