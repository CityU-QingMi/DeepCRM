	@Test
	public void testBuildMessageWithoutIdAndTimestamp() {
		MessageHeaderAccessor headerAccessor = new MessageHeaderAccessor();
		headerAccessor.setIdGenerator(new IdGenerator() {
			@Override
			public UUID generateId() {
				return MessageHeaders.ID_VALUE_NONE;
			}
		});
		Message<?> message = MessageBuilder.createMessage("foo", headerAccessor.getMessageHeaders());
		assertNull(message.getHeaders().getId());
		assertNull(message.getHeaders().getTimestamp());
	}
