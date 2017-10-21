	@Test
	public void findSubscriptionsReturnsMapSafeToIterateIncludingValues() throws Exception {
		this.registry.registerSubscription(subscribeMessage("sess1", "1", "/foo"));
		this.registry.registerSubscription(subscribeMessage("sess1", "2", "/foo"));

		MultiValueMap<String, String> allSubscriptions = this.registry.findSubscriptions(createMessage("/foo"));
		assertNotNull(allSubscriptions);
		assertEquals(1, allSubscriptions.size());

		Iterator<String> iteratorValues = allSubscriptions.get("sess1").iterator();
		iteratorValues.next();

		this.registry.unregisterSubscription(unsubscribeMessage("sess1", "2"));

		iteratorValues.next();
		// no ConcurrentModificationException
	}
