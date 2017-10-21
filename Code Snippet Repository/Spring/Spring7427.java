	@Test
	public void customMessageConverterFailure() throws Exception {
		DefaultMessageHandlerMethodFactory instance = createInstance();
		MessageConverter messageConverter = new ByteArrayMessageConverter();
		instance.setMessageConverter(messageConverter);
		instance.afterPropertiesSet();

		InvocableHandlerMethod invocableHandlerMethod =
				createInvocableHandlerMethod(instance, "simpleString", String.class);

		thrown.expect(MessageConversionException.class);
		invocableHandlerMethod.invoke(MessageBuilder.withPayload(123).build());
	}
