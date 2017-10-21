	@Test
	public void multipleSessionIds() throws Exception {
		WebSession existing = this.updateSession;
		String id = existing.getId();
		when(this.store.retrieveSession(any())).thenReturn(Mono.empty());
		when(this.store.retrieveSession(id)).thenReturn(Mono.just(existing));
		when(this.idResolver.resolveSessionIds(this.exchange)).thenReturn(Arrays.asList("neither-this", "nor-that", id));

		WebSession actual = this.manager.getSession(this.exchange).block();
		assertNotNull(actual);
		assertEquals(existing.getId(), actual.getId());
	}
