	@Test
	public void findSubscriptions() throws Exception {
		DefaultSimpUserRegistry registry = new DefaultSimpUserRegistry();

		TestPrincipal user = new TestPrincipal("joe");
		Message<byte[]> message = createMessage(SimpMessageType.CONNECT_ACK, "123");
		SessionConnectedEvent event = new SessionConnectedEvent(this, message, user);
		registry.onApplicationEvent(event);

		message = createMessage(SimpMessageType.SUBSCRIBE, "123", "sub1", "/match");
		SessionSubscribeEvent subscribeEvent = new SessionSubscribeEvent(this, message, user);
		registry.onApplicationEvent(subscribeEvent);

		message = createMessage(SimpMessageType.SUBSCRIBE, "123", "sub2", "/match");
		subscribeEvent = new SessionSubscribeEvent(this, message, user);
		registry.onApplicationEvent(subscribeEvent);

		message = createMessage(SimpMessageType.SUBSCRIBE, "123", "sub3", "/not-a-match");
		subscribeEvent = new SessionSubscribeEvent(this, message, user);
		registry.onApplicationEvent(subscribeEvent);

		Set<SimpSubscription> matches = registry.findSubscriptions(new SimpSubscriptionMatcher() {
			@Override
			public boolean match(SimpSubscription subscription) {
				return subscription.getDestination().equals("/match");
			}
		});

		assertEquals(2, matches.size());

		Iterator<SimpSubscription> iterator = matches.iterator();
		Set<String> sessionIds = new HashSet<>(2);
		sessionIds.add(iterator.next().getId());
		sessionIds.add(iterator.next().getId());
		assertEquals(new HashSet<>(Arrays.asList("sub1", "sub2")), sessionIds);
	}
