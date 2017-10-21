	@Test
	public void configureMessageConvertersDefault() {
		AbstractMessageBrokerConfiguration config = new BaseTestMessageBrokerConfig();
		CompositeMessageConverter compositeConverter = config.brokerMessageConverter();

		List<MessageConverter> converters = compositeConverter.getConverters();
		assertThat(converters.size(), Matchers.is(3));
		assertThat(converters.get(0), Matchers.instanceOf(StringMessageConverter.class));
		assertThat(converters.get(1), Matchers.instanceOf(ByteArrayMessageConverter.class));
		assertThat(converters.get(2), Matchers.instanceOf(MappingJackson2MessageConverter.class));

		ContentTypeResolver resolver = ((MappingJackson2MessageConverter) converters.get(2)).getContentTypeResolver();
		assertEquals(MimeTypeUtils.APPLICATION_JSON, ((DefaultContentTypeResolver) resolver).getDefaultMimeType());
	}
