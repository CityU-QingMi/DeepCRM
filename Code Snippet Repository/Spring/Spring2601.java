	private void registerAsyncExecutionAspect(Element element, ParserContext parserContext) {
		if (!parserContext.getRegistry().containsBeanDefinition(TaskManagementConfigUtils.ASYNC_EXECUTION_ASPECT_BEAN_NAME)) {
			BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(ASYNC_EXECUTION_ASPECT_CLASS_NAME);
			builder.setFactoryMethod("aspectOf");
			String executor = element.getAttribute("executor");
			if (StringUtils.hasText(executor)) {
				builder.addPropertyReference("executor", executor);
			}
			String exceptionHandler = element.getAttribute("exception-handler");
			if (StringUtils.hasText(exceptionHandler)) {
				builder.addPropertyReference("exceptionHandler", exceptionHandler);
			}
			parserContext.registerBeanComponent(new BeanComponentDefinition(builder.getBeanDefinition(),
					TaskManagementConfigUtils.ASYNC_EXECUTION_ASPECT_BEAN_NAME));
		}
	}
