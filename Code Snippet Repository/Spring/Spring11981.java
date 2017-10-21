	@Nullable
	public static Object getContentNegotiationManager(ParserContext context) {
		String name = AnnotationDrivenBeanDefinitionParser.HANDLER_MAPPING_BEAN_NAME;
		if (context.getRegistry().containsBeanDefinition(name)) {
			BeanDefinition handlerMappingBeanDef = context.getRegistry().getBeanDefinition(name);
			return handlerMappingBeanDef.getPropertyValues().get("contentNegotiationManager");
		}
		name = AnnotationDrivenBeanDefinitionParser.CONTENT_NEGOTIATION_MANAGER_BEAN_NAME;
		if (context.getRegistry().containsBeanDefinition(name)) {
			return new RuntimeBeanReference(name);
		}
		return null;
	}
