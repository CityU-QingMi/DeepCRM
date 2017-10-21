	@Test
	public void testDoesNotDelegateToFallbackIfNotResolvedInJndi() throws Exception {
		final Session session = mock(Session.class);
		DestinationResolver dynamicResolver = mock(DestinationResolver.class);

		final JndiDestinationResolver resolver = new JndiDestinationResolver() {
			@Override
			protected <T> T lookup(String jndiName, Class<T> requiredClass) throws NamingException {
				throw new NamingException();
			}
		};
		resolver.setDynamicDestinationResolver(dynamicResolver);

		try {
			resolver.resolveDestinationName(session, DESTINATION_NAME, true);
			fail("expected DestinationResolutionException");
		}
		catch (DestinationResolutionException ex) {
			// expected
		}
	}
