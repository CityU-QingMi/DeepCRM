	@Test
	public void customConversionServiceFailure() throws Exception {
		DefaultMessageHandlerMethodFactory instance = createInstance();
		GenericConversionService conversionService = new GenericConversionService();
		assertFalse("conversion service should fail to convert payload",
				conversionService.canConvert(Integer.class, String.class));
		instance.setConversionService(conversionService);
		instance.afterPropertiesSet();

		InvocableHandlerMethod invocableHandlerMethod =
				createInvocableHandlerMethod(instance, "simpleString", String.class);

		thrown.expect(MessageConversionException.class);
		invocableHandlerMethod.invoke(MessageBuilder.withPayload(123).build());
	}
