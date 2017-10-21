	@Test
	public void testChokesIfDestinationResolverIsetToNullExplcitly() throws Exception {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);

		try {
			JmsDestinationAccessor accessor = new StubJmsDestinationAccessor();
			accessor.setConnectionFactory(connectionFactory);
			accessor.setDestinationResolver(null);
			accessor.afterPropertiesSet();
			fail("expected IllegalArgumentException");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}

	}
