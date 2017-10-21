	@Test
	public void receiveAndConvertNoConverter() {
		Message<?> expected = new GenericMessage<>("payload");
		this.template.setDefaultDestination("home");
		this.template.setReceiveMessage(expected);
		this.template.setMessageConverter(new GenericMessageConverter());
		try {
			this.template.receiveAndConvert(Writer.class);
		}
		catch (MessageConversionException ex) {
			assertTrue("Invalid exception message '" + ex.getMessage() + "'", ex.getMessage().contains("payload"));
			assertSame(expected, ex.getFailedMessage());
		}
	}
