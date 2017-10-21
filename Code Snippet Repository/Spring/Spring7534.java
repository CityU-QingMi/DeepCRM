	@Test
	public void registerSubscriptionTwiceAndUnregister() {
		this.registry.registerSubscription(subscribeMessage("sess01", "subs01", "/foo"));
		this.registry.registerSubscription(subscribeMessage("sess01", "subs02", "/foo"));

		MultiValueMap<String, String> actual = this.registry.findSubscriptions(createMessage("/foo"));
		assertNotNull(actual);
		assertEquals("Expected 1 element", 1, actual.size());
		assertEquals(Arrays.asList("subs01", "subs02"), actual.get("sess01"));

		this.registry.unregisterSubscription(unsubscribeMessage("sess01", "subs01"));

		actual = this.registry.findSubscriptions(createMessage("/foo"));
		assertNotNull(actual);
		assertEquals("Expected 1 element", 1, actual.size());
		assertEquals(Collections.singletonList("subs02"), actual.get("sess01"));

		this.registry.unregisterSubscription(unsubscribeMessage("sess01", "subs02"));

		actual = this.registry.findSubscriptions(createMessage("/foo"));
		assertNotNull(actual);
		assertEquals("Expected no element", 0, actual.size());
	}
