	@Test
	public void noValidationByDefault() throws Exception {
		DefaultMessageHandlerMethodFactory instance = createInstance();
		instance.afterPropertiesSet();

		InvocableHandlerMethod invocableHandlerMethod =
				createInvocableHandlerMethod(instance, "payloadValidation", String.class);
		invocableHandlerMethod.invoke(MessageBuilder.withPayload("failure").build());
		assertMethodInvocation(sample, "payloadValidation");
	}
