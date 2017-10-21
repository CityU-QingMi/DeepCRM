	private void assertResponse(MethodParameter methodParameter, String sessionId,
			int index, String destination) {

		SimpMessageHeaderAccessor accessor = getCapturedAccessor(index);
		assertEquals(sessionId, accessor.getSessionId());
		assertEquals(destination, accessor.getDestination());
		assertEquals(MIME_TYPE, accessor.getContentType());
		assertNull("Subscription id should not be copied", accessor.getSubscriptionId());
		assertEquals(methodParameter, accessor.getHeader(SimpMessagingTemplate.CONVERSION_HINT_HEADER));
	}
