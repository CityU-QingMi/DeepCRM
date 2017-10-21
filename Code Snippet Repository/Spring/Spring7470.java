	@Test
	public void processHeadersToSend() {
		Map<String, Object> map = this.messagingTemplate.processHeadersToSend(null);

		assertNotNull(map);
		assertTrue("Actual: " + map.getClass().toString(), MessageHeaders.class.isAssignableFrom(map.getClass()));

		SimpMessageHeaderAccessor headerAccessor =
				MessageHeaderAccessor.getAccessor((MessageHeaders) map, SimpMessageHeaderAccessor.class);

		assertTrue(headerAccessor.isMutable());
		assertEquals(SimpMessageType.MESSAGE, headerAccessor.getMessageType());
	}
