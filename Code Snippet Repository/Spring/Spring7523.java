	@Test
	public void findSubscriptionsReturnsMapSafeToIterate() throws Exception {
		this.registry.registerSubscription(subscribeMessage("sess1", "1", "/foo"));
		this.registry.registerSubscription(subscribeMessage("sess2", "1", "/foo"));

		MultiValueMap<String, String> subscriptions = this.registry.findSubscriptions(createMessage("/foo"));
		assertNotNull(subscriptions);
		assertEquals(2, subscriptions.size());

		Iterator<Map.Entry<String, List<String>>> iterator = subscriptions.entrySet().iterator();
		iterator.next();

		this.registry.registerSubscription(subscribeMessage("sess3", "1", "/foo"));

		iterator.next();
		// no ConcurrentModificationException
	}
