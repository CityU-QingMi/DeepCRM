	@Test(expected = MessageConversionException.class)
	public void convertAndSendNoMatchingConverter() {

		MessageConverter converter = new CompositeMessageConverter(
				Arrays.<MessageConverter>asList(new MappingJackson2MessageConverter()));
		this.template.setMessageConverter(converter);

		this.headers.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_XML);
		this.template.convertAndSend("home", "payload", new MessageHeaders(this.headers));
	}
