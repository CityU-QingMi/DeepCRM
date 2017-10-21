	@Test
	public void getUserFromLocalRegistry() throws Exception {
		SimpUser user = Mockito.mock(SimpUser.class);
		Set<SimpUser> users = Collections.singleton(user);
		when(this.localRegistry.getUsers()).thenReturn(users);
		when(this.localRegistry.getUserCount()).thenReturn(1);
		when(this.localRegistry.getUser("joe")).thenReturn(user);

		assertEquals(1, this.registry.getUserCount());
		assertSame(user, this.registry.getUser("joe"));
	}
