		public void updateAfterNewSubscription(String destination, String sessionId, String subsId) {
			synchronized (this.updateCache) {
				for (Map.Entry<String, LinkedMultiValueMap<String, String>> entry : this.updateCache.entrySet()) {
					String cachedDestination = entry.getKey();
					if (getPathMatcher().match(destination, cachedDestination)) {
						LinkedMultiValueMap<String, String> subs = entry.getValue();
						// Subscription id's may also be populated via getSubscriptions()
						List<String> subsForSession = subs.get(sessionId);
						if (subsForSession == null || !subsForSession.contains(subsId)) {
							subs.add(sessionId, subsId);
							this.accessCache.put(cachedDestination, subs.deepCopy());
						}
					}
				}
			}
		}
