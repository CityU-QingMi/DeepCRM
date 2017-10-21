	public static RuntimeBeanReference registerCorsConfigurations(
			@Nullable Map<String, CorsConfiguration> corsConfigurations,
			ParserContext parserContext, @Nullable Object source) {

		if (!parserContext.getRegistry().containsBeanDefinition(CORS_CONFIGURATION_BEAN_NAME)) {
			RootBeanDefinition corsConfigurationsDef = new RootBeanDefinition(LinkedHashMap.class);
			corsConfigurationsDef.setSource(source);
			corsConfigurationsDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			if (corsConfigurations != null) {
				corsConfigurationsDef.getConstructorArgumentValues().addIndexedArgumentValue(0, corsConfigurations);
			}
			parserContext.getReaderContext().getRegistry().registerBeanDefinition(CORS_CONFIGURATION_BEAN_NAME, corsConfigurationsDef);
			parserContext.registerComponent(new BeanComponentDefinition(corsConfigurationsDef, CORS_CONFIGURATION_BEAN_NAME));
		}
		else if (corsConfigurations != null) {
			BeanDefinition corsConfigurationsDef = parserContext.getRegistry().getBeanDefinition(CORS_CONFIGURATION_BEAN_NAME);
			corsConfigurationsDef.getConstructorArgumentValues().addIndexedArgumentValue(0, corsConfigurations);
		}
		return new RuntimeBeanReference(CORS_CONFIGURATION_BEAN_NAME);
	}
