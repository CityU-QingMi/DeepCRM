	@Test
	public void registerSubscriptionWithSelector() throws Exception {
		String sessionId = "sess01";
		String subscriptionId = "subs01";
		String destination = "/foo";
		String selector = "headers.foo == 'bar'";

		this.registry.registerSubscription(subscribeMessage(sessionId, subscriptionId, destination, selector));

		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create();
		accessor.setDestination(destination);
		accessor.setNativeHeader("foo", "bar");
		Message<?> message = MessageBuilder.createMessage("", accessor.getMessageHeaders());

		MultiValueMap<String, String> actual = this.registry.findSubscriptions(message);
		assertNotNull(actual);
		assertEquals(1, actual.size());
		assertEquals(Collections.singletonList(subscriptionId), actual.get(sessionId));

		actual = this.registry.findSubscriptions(createMessage(destination));
		assertNotNull(actual);
		assertEquals(0, actual.size());
	}
