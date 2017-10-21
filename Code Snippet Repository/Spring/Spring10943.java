	@Test
	public void existingSession() throws Exception {
		String id = this.updateSession.getId();
		when(this.store.retrieveSession(id)).thenReturn(Mono.just(this.updateSession));
		when(this.idResolver.resolveSessionIds(this.exchange)).thenReturn(Collections.singletonList(id));

		WebSession actual = this.manager.getSession(this.exchange).block();
		assertNotNull(actual);
		assertEquals(id, actual.getId());
	}
