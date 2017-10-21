	@Test
	public void registerSubscriptionOneSession() {
		String sessId = "sess01";
		List<String> subscriptionIds = Arrays.asList("subs01", "subs02", "subs03");
		String dest = "/foo";

		for (String subId : subscriptionIds) {
			this.registry.registerSubscription(subscribeMessage(sessId, subId, dest));
		}

		MultiValueMap<String, String> actual = this.registry.findSubscriptions(createMessage(dest));
		assertNotNull(actual);
		assertEquals(1, actual.size());
		assertEquals(subscriptionIds, sort(actual.get(sessId)));
	}
