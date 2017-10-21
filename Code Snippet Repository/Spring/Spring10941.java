	@Test
	public void getSessionSaveWhenCreatedAndStartedThenSavesAndSetsId() throws Exception {
		when(this.idResolver.resolveSessionIds(this.exchange)).thenReturn(Collections.emptyList());
		WebSession session = this.manager.getSession(this.exchange).block();
		when(this.createSession.isStarted()).thenReturn(true);
		this.exchange.getResponse().setComplete().block();

		String id = session.getId();
		verify(this.store).createWebSession();
		verify(this.createSession).save();
		verify(this.idResolver).setSessionId(any(), eq(id));
	}
