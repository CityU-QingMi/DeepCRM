	private static void registerHandlerMappingIntrospector(ParserContext parserContext, @Nullable Object source) {
		if (!parserContext.getRegistry().containsBeanDefinition(HANDLER_MAPPING_INTROSPECTOR_BEAN_NAME)){
			RootBeanDefinition beanDef = new RootBeanDefinition(HandlerMappingIntrospector.class);
			beanDef.setSource(source);
			beanDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			beanDef.setLazyInit(true);
			parserContext.getRegistry().registerBeanDefinition(HANDLER_MAPPING_INTROSPECTOR_BEAN_NAME, beanDef);
			parserContext.registerComponent(new BeanComponentDefinition(beanDef, HANDLER_MAPPING_INTROSPECTOR_BEAN_NAME));
		}
	}
