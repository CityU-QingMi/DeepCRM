		public LinkedMultiValueMap<String, String> getSubscriptions(String destination, Message<?> message) {
			LinkedMultiValueMap<String, String> result = this.accessCache.get(destination);
			if (result == null) {
				synchronized (this.updateCache) {
					result = new LinkedMultiValueMap<>();
					for (SessionSubscriptionInfo info : subscriptionRegistry.getAllSubscriptions()) {
						for (String destinationPattern : info.getDestinations()) {
							if (getPathMatcher().match(destinationPattern, destination)) {
								for (Subscription subscription : info.getSubscriptions(destinationPattern)) {
									result.add(info.sessionId, subscription.getId());
								}
							}
						}
					}
					if (!result.isEmpty()) {
						this.updateCache.put(destination, result.deepCopy());
						this.accessCache.put(destination, result);
					}
				}
			}
			return result;
		}
