	@Test
	public void testHitsCacheSecondTimeThrough() throws Exception {

		Session session = mock(Session.class);

		JndiDestinationResolver resolver = new OneTimeLookupJndiDestinationResolver();
		Destination destination = resolver.resolveDestinationName(session, DESTINATION_NAME, true);
		assertNotNull(destination);
		assertSame(DESTINATION, destination);
	}
