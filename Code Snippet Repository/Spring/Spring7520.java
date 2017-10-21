	@Test
	public void registerSubscriptionInvalidInput() {
		String sessId = "sess01";
		String subsId = "subs01";
		String dest = "/foo";

		this.registry.registerSubscription(subscribeMessage(null, subsId, dest));
		MultiValueMap<String, String> actual = this.registry.findSubscriptions(createMessage(dest));
		assertNotNull(actual);
		assertEquals(0, actual.size());

		this.registry.registerSubscription(subscribeMessage(sessId, null, dest));
		actual = this.registry.findSubscriptions(createMessage(dest));
		assertNotNull(actual);
		assertEquals(0, actual.size());

		this.registry.registerSubscription(subscribeMessage(sessId, subsId, null));
		actual = this.registry.findSubscriptions(createMessage(dest));
		assertNotNull(actual);
		assertEquals(0, actual.size());
	}
