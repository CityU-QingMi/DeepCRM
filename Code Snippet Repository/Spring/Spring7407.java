	@Test
	public void receiveAndConvertFailed() {
		Message<?> expected = new GenericMessage<>("not a number test");
		this.template.setReceiveMessage(expected);
		this.template.setMessageConverter(new GenericMessageConverter());

		thrown.expect(MessageConversionException.class);
		thrown.expectCause(isA(ConversionFailedException.class));
		this.template.receiveAndConvert("somewhere", Integer.class);
	}
