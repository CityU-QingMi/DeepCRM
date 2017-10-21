	@Test
	public void combine() {
		SimpMessageType actual = condition(SimpMessageType.MESSAGE).combine(condition(SimpMessageType.SUBSCRIBE)).getMessageType();
		assertEquals(SimpMessageType.SUBSCRIBE, actual);

		actual = condition(SimpMessageType.MESSAGE).combine(condition(SimpMessageType.MESSAGE)).getMessageType();
		assertEquals(SimpMessageType.MESSAGE, actual);

		actual = condition(SimpMessageType.SUBSCRIBE).combine(condition(SimpMessageType.SUBSCRIBE)).getMessageType();
		assertEquals(SimpMessageType.SUBSCRIBE, actual);
	}
