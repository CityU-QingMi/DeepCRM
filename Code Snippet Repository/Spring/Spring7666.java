	@Test
	public void findSubscriptionsFromRemoteRegistry() throws Exception {
		// Prepare broadcast message from remote server
		TestSimpUser user1 = new TestSimpUser("joe");
		TestSimpUser user2 = new TestSimpUser("jane");
		TestSimpUser user3 = new TestSimpUser("jack");
		TestSimpSession session1 = new TestSimpSession("sess1");
		TestSimpSession session2 = new TestSimpSession("sess2");
		TestSimpSession session3 = new TestSimpSession("sess3");
		session1.addSubscriptions(new TestSimpSubscription("sub1", "/match"));
		session2.addSubscriptions(new TestSimpSubscription("sub1", "/match"));
		session3.addSubscriptions(new TestSimpSubscription("sub1", "/not-a-match"));
		user1.addSessions(session1);
		user2.addSessions(session2);
		user3.addSessions(session3);
		SimpUserRegistry userRegistry = mock(SimpUserRegistry.class);
		when(userRegistry.getUsers()).thenReturn(new HashSet<>(Arrays.asList(user1, user2, user3)));
		Object registryDto = new MultiServerUserRegistry(userRegistry).getLocalRegistryDto();
		Message<?> message = this.converter.toMessage(registryDto, null);

		// Add remote registry
		this.registry.addRemoteRegistryDto(message, this.converter, 20000);

		assertEquals(3, this.registry.getUserCount());
		Set<SimpSubscription> matches = this.registry.findSubscriptions(s -> s.getDestination().equals("/match"));
		assertEquals(2, matches.size());
		Iterator<SimpSubscription> iterator = matches.iterator();
		Set<String> sessionIds = new HashSet<>(2);
		sessionIds.add(iterator.next().getSession().getId());
		sessionIds.add(iterator.next().getSession().getId());
		assertEquals(new HashSet<>(Arrays.asList("sess1", "sess2")), sessionIds);
	}
