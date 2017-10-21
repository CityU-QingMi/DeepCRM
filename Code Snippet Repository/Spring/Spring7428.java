	@Test
	public void customArgumentResolver() throws Exception {
		DefaultMessageHandlerMethodFactory instance = createInstance();
		List<HandlerMethodArgumentResolver> customResolvers = new ArrayList<>();
		customResolvers.add(new CustomHandlerMethodArgumentResolver());
		instance.setCustomArgumentResolvers(customResolvers);
		instance.afterPropertiesSet();

		InvocableHandlerMethod invocableHandlerMethod =
				createInvocableHandlerMethod(instance, "customArgumentResolver", Locale.class);

		invocableHandlerMethod.invoke(MessageBuilder.withPayload(123).build());
		assertMethodInvocation(sample, "customArgumentResolver");
	}
