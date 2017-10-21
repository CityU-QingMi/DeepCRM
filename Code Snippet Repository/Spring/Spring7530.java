	@Test
	public void registerSubscriptionWithDestinationPattern() {
		String sessId = "sess01";
		String subsId = "subs01";
		String destPattern = "/topic/PRICE.STOCK.*.IBM";
		String dest = "/topic/PRICE.STOCK.NASDAQ.IBM";
		this.registry.registerSubscription(subscribeMessage(sessId, subsId, destPattern));

		MultiValueMap<String, String> actual = this.registry.findSubscriptions(createMessage(dest));
		assertNotNull(actual);
		assertEquals("Expected one element " + actual, 1, actual.size());
		assertEquals(Collections.singletonList(subsId), actual.get(sessId));
	}
