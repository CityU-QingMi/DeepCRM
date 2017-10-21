	@Test
	public void registerSubscriptionWithDestinationPatternRegex() {
		String sessId = "sess01";
		String subsId = "subs01";
		String destPattern = "/topic/PRICE.STOCK.*.{ticker:(IBM|MSFT)}";

		this.registry.registerSubscription(subscribeMessage(sessId, subsId, destPattern));
		Message<?> message = createMessage("/topic/PRICE.STOCK.NASDAQ.IBM");
		MultiValueMap<String, String> actual = this.registry.findSubscriptions(message);
		assertNotNull(actual);
		assertEquals("Expected one element " + actual, 1, actual.size());
		assertEquals(Collections.singletonList(subsId), actual.get(sessId));

		message = createMessage("/topic/PRICE.STOCK.NASDAQ.MSFT");
		actual = this.registry.findSubscriptions(message);
		assertNotNull(actual);
		assertEquals("Expected one element " + actual, 1, actual.size());
		assertEquals(Collections.singletonList(subsId), actual.get(sessId));

		message = createMessage("/topic/PRICE.STOCK.NASDAQ.VMW");
		actual = this.registry.findSubscriptions(message);
		assertNotNull(actual);
		assertEquals("Expected no elements " + actual, 0, actual.size());
	}
