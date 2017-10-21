	@Test
	public void sendMessageInternal() throws Exception {

		this.session.initializeDelegateSession(this.webSocketSession);
		this.session.sendMessageInternal("x");

		assertEquals(Arrays.asList(new TextMessage("o"), new TextMessage("a[\"x\"]")),
				this.webSocketSession.getSentMessages());

		assertEquals(Arrays.asList("schedule", "cancel", "schedule"), this.session.heartbeatSchedulingEvents);
	}
