		public void addSubscription(String destination, String subscriptionId, @Nullable Expression selectorExpression) {
			Set<Subscription> subs = this.destinationLookup.get(destination);
			if (subs == null) {
				synchronized (this.destinationLookup) {
					subs = this.destinationLookup.get(destination);
					if (subs == null) {
						subs = new CopyOnWriteArraySet<>();
						this.destinationLookup.put(destination, subs);
					}
				}
			}
			subs.add(new Subscription(subscriptionId, selectorExpression));
		}
