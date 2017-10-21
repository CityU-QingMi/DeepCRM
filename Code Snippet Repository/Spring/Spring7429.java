	@Test
	public void overrideArgumentResolvers() throws Exception {
		DefaultMessageHandlerMethodFactory instance = createInstance();
		List<HandlerMethodArgumentResolver> customResolvers = new ArrayList<>();
		customResolvers.add(new CustomHandlerMethodArgumentResolver());
		instance.setArgumentResolvers(customResolvers);
		instance.afterPropertiesSet();

		Message<String> message = MessageBuilder.withPayload("sample").build();

		// This will work as the local resolver is set
		InvocableHandlerMethod invocableHandlerMethod =
				createInvocableHandlerMethod(instance, "customArgumentResolver", Locale.class);
		invocableHandlerMethod.invoke(message);
		assertMethodInvocation(sample, "customArgumentResolver");

		// This won't work as no resolver is known for the payload
		InvocableHandlerMethod invocableHandlerMethod2 =
				createInvocableHandlerMethod(instance, "simpleString", String.class);

		thrown.expect(MethodArgumentResolutionException.class);
		thrown.expectMessage("No suitable resolver for");
		invocableHandlerMethod2.invoke(message);
	}
