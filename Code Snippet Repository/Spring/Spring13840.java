	@Override
	protected MappingJackson2MessageConverter createJacksonConverter() {
		MappingJackson2MessageConverter messageConverter = super.createJacksonConverter();
		// Use Jackson builder in order to have JSR-310 and Joda-Time modules registered automatically
		Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
		ApplicationContext applicationContext = getApplicationContext();
		if (applicationContext != null) {
			builder.applicationContext(applicationContext);
		}
		messageConverter.setObjectMapper(builder.build());
		return messageConverter;
	}
