	@Test
	public void exchangeWhenResponseSetCompleteThenSavesAndSetsId() throws Exception {
		when(this.idResolver.resolveSessionIds(this.exchange)).thenReturn(Collections.emptyList());
		String id = this.createSession.getId();
		WebSession session = this.manager.getSession(this.exchange).block();
		when(this.createSession.isStarted()).thenReturn(true);
		this.exchange.getResponse().setComplete().block();

		verify(this.idResolver).setSessionId(any(), eq(id));
		verify(this.createSession).save();
	}
