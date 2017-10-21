	@Test
	public void getMessageType() throws Exception {
		for (StompCommand stompCommand : StompCommand.values()) {
			SimpMessageType simp = messageTypes.get(stompCommand);
			if (simp == null) {
				simp = SimpMessageType.OTHER;
			}
			assertSame(simp, stompCommand.getMessageType());
		}
	}
