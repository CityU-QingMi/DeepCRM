	@Test
	public void messageIdAndTimestampEnabled() {
		IdTimestampMessageHeaderInitializer headerInitializer = new IdTimestampMessageHeaderInitializer();
		headerInitializer.setIdGenerator(new AlternativeJdkIdGenerator());
		headerInitializer.setEnableTimestamp(true);

		StompHeaderAccessor headerAccessor = StompHeaderAccessor.create(StompCommand.SEND);
		headerInitializer.initHeaders(headerAccessor);

		assertNotNull(headerAccessor.getMessageHeaders().getId());
		assertNotNull(headerAccessor.getMessageHeaders().getTimestamp());
	}
