	@Test
	public void registerSubscription() {
		String sessId = "sess01";
		String subsId = "subs01";
		String dest = "/foo";

		this.registry.registerSubscription(subscribeMessage(sessId, subsId, dest));

		MultiValueMap<String, String> actual = this.registry.findSubscriptions(createMessage(dest));
		assertNotNull(actual);
		assertEquals("Expected one element " + actual, 1, actual.size());
		assertEquals(Collections.singletonList(subsId), actual.get(sessId));
	}
