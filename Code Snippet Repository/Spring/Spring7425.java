	@Test
	public void customConversion() throws Exception {
		DefaultMessageHandlerMethodFactory instance = createInstance();
		GenericConversionService conversionService = new GenericConversionService();
		conversionService.addConverter(SampleBean.class, String.class, new Converter<SampleBean, String>() {
			@Override
			public String convert(SampleBean source) {
				return "foo bar";
			}
		});
		instance.setConversionService(conversionService);
		instance.afterPropertiesSet();

		InvocableHandlerMethod invocableHandlerMethod =
				createInvocableHandlerMethod(instance, "simpleString", String.class);

		invocableHandlerMethod.invoke(MessageBuilder.withPayload(sample).build());
		assertMethodInvocation(sample, "simpleString");
	}
