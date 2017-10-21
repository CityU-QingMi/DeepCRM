	@Test
	public void idTimestampWithMutableHeaders() {
		MessageHeaderAccessor accessor = new MessageHeaderAccessor();
		accessor.setIdGenerator(() -> MessageHeaders.ID_VALUE_NONE);
		accessor.setEnableTimestamp(false);
		accessor.setLeaveMutable(true);
		MessageHeaders headers = accessor.getMessageHeaders();

		assertNull(headers.getId());
		assertNull(headers.getTimestamp());

		final UUID id = new UUID(0L, 23L);
		accessor.setIdGenerator(() -> id);
		accessor.setEnableTimestamp(true);
		accessor.setImmutable();

		assertSame(id, accessor.getMessageHeaders().getId());
		assertNotNull(headers.getTimestamp());
	}
