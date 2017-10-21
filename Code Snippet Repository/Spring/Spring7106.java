	@Test
	public void testDelegatesToFallbackIfNotResolvedInJndi() throws Exception {
		Session session = mock(Session.class);

		DestinationResolver dynamicResolver = mock(DestinationResolver.class);
		given(dynamicResolver.resolveDestinationName(session, DESTINATION_NAME,
				true)).willReturn(DESTINATION);

		JndiDestinationResolver resolver = new JndiDestinationResolver() {
			@Override
			protected <T> T lookup(String jndiName, Class<T> requiredClass) throws NamingException {
				throw new NamingException();
			}
		};
		resolver.setFallbackToDynamicDestination(true);
		resolver.setDynamicDestinationResolver(dynamicResolver);
		Destination destination = resolver.resolveDestinationName(session, DESTINATION_NAME, true);

		assertNotNull(destination);
		assertSame(DESTINATION, destination);
	}
