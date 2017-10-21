	@Test
	public void cachedDestination() {
		@SuppressWarnings("unchecked")
		DestinationResolver<String> destinationResolver = mock(DestinationResolver.class);
		CachingDestinationResolverProxy<String> cachingDestinationResolver = new CachingDestinationResolverProxy<>(destinationResolver);

		given(destinationResolver.resolveDestination("abcd")).willReturn("dcba");
		given(destinationResolver.resolveDestination("1234")).willReturn("4321");

		assertEquals("dcba", cachingDestinationResolver.resolveDestination("abcd"));
		assertEquals("4321", cachingDestinationResolver.resolveDestination("1234"));
		assertEquals("4321", cachingDestinationResolver.resolveDestination("1234"));
		assertEquals("dcba", cachingDestinationResolver.resolveDestination("abcd"));

		verify(destinationResolver, times(1)).resolveDestination("abcd");
		verify(destinationResolver, times(1)).resolveDestination("1234");
	}
