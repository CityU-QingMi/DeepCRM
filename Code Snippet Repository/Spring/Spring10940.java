	@Test
	public void getSessionSaveWhenCreatedAndNotStartedThenNotSaved() throws Exception {
		when(this.idResolver.resolveSessionIds(this.exchange)).thenReturn(Collections.emptyList());
		WebSession session = this.manager.getSession(this.exchange).block();
		this.exchange.getResponse().setComplete().block();

		assertFalse(session.isStarted());
		assertFalse(session.isExpired());
		verify(this.createSession, never()).save();
		verify(this.idResolver, never()).setSessionId(any(), any());
	}
