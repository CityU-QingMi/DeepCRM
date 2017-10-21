	@Override
	@Nullable
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		Object source = parserContext.extractSource(element);

		// Register component for the surrounding <jms:annotation-driven> element.
		CompositeComponentDefinition compDefinition = new CompositeComponentDefinition(element.getTagName(), source);
		parserContext.pushContainingComponent(compDefinition);

		// Nest the concrete post-processor bean in the surrounding component.
		BeanDefinitionRegistry registry = parserContext.getRegistry();

		if (registry.containsBeanDefinition(JmsListenerConfigUtils.JMS_LISTENER_ANNOTATION_PROCESSOR_BEAN_NAME)) {
			parserContext.getReaderContext().error(
					"Only one JmsListenerAnnotationBeanPostProcessor may exist within the context.", source);
		}
		else {
			BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(
					"org.springframework.jms.annotation.JmsListenerAnnotationBeanPostProcessor");
			builder.getRawBeanDefinition().setSource(source);
			String endpointRegistry = element.getAttribute("registry");
			if (StringUtils.hasText(endpointRegistry)) {
				builder.addPropertyReference("endpointRegistry", endpointRegistry);
			}
			else {
				registerDefaultEndpointRegistry(source, parserContext);
			}

			String containerFactory = element.getAttribute("container-factory");
			if (StringUtils.hasText(containerFactory)) {
				builder.addPropertyValue("containerFactoryBeanName", containerFactory);
			}

			String handlerMethodFactory = element.getAttribute("handler-method-factory");
			if (StringUtils.hasText(handlerMethodFactory)) {
				builder.addPropertyReference("messageHandlerMethodFactory", handlerMethodFactory);
			}

			registerInfrastructureBean(parserContext, builder,
					JmsListenerConfigUtils.JMS_LISTENER_ANNOTATION_PROCESSOR_BEAN_NAME);
		}

		// Finally register the composite component.
		parserContext.popAndRegisterContainingComponent();

		return null;
	}
