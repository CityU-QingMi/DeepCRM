	@Test
	public void cacheLimitExceeded() throws Exception {
		this.registry.setCacheLimit(1);
		this.registry.registerSubscription(subscribeMessage("sess1", "1", "/foo"));
		this.registry.registerSubscription(subscribeMessage("sess1", "2", "/bar"));

		assertEquals(1, this.registry.findSubscriptions(createMessage("/foo")).size());
		assertEquals(1, this.registry.findSubscriptions(createMessage("/bar")).size());

		this.registry.registerSubscription(subscribeMessage("sess2", "1", "/foo"));
		this.registry.registerSubscription(subscribeMessage("sess2", "2", "/bar"));

		assertEquals(2, this.registry.findSubscriptions(createMessage("/foo")).size());
		assertEquals(2, this.registry.findSubscriptions(createMessage("/bar")).size());
	}
