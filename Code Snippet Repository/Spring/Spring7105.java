	@Test
	public void testDoesNotUseCacheIfCachingIsTurnedOff() throws Exception {

		Session session = mock(Session.class);

		CountingCannedJndiDestinationResolver resolver
				= new CountingCannedJndiDestinationResolver();
		resolver.setCache(false);
		Destination destination = resolver.resolveDestinationName(session, DESTINATION_NAME, true);
		assertNotNull(destination);
		assertSame(DESTINATION, destination);
		assertEquals(1, resolver.getCallCount());

		destination = resolver.resolveDestinationName(session, DESTINATION_NAME, true);
		assertNotNull(destination);
		assertSame(DESTINATION, destination);
		assertEquals(2, resolver.getCallCount());
	}
