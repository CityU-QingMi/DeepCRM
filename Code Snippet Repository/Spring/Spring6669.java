	@Override
	protected RootBeanDefinition createContainer(Element containerEle, Element listenerEle, ParserContext parserContext,
			PropertyValues commonContainerProperties, PropertyValues specificContainerProperties) {

		RootBeanDefinition containerDef = new RootBeanDefinition();
		containerDef.setSource(parserContext.extractSource(containerEle));
		containerDef.getPropertyValues().addPropertyValues(commonContainerProperties);
		containerDef.getPropertyValues().addPropertyValues(specificContainerProperties);

		String containerType = containerEle.getAttribute(CONTAINER_TYPE_ATTRIBUTE);
		String containerClass = containerEle.getAttribute(CONTAINER_CLASS_ATTRIBUTE);
		if (!"".equals(containerClass)) {
			containerDef.setBeanClassName(containerClass);
		}
		else if ("".equals(containerType) || containerType.startsWith("default")) {
			containerDef.setBeanClassName("org.springframework.jms.listener.DefaultMessageListenerContainer");
		}
		else if (containerType.startsWith("simple")) {
			containerDef.setBeanClassName("org.springframework.jms.listener.SimpleMessageListenerContainer");
		}
		else {
			parserContext.getReaderContext().error(
					"Invalid 'container-type' attribute: only \"default\" and \"simple\" supported.", containerEle);
		}

		// Parse listener specific settings
		parseListenerConfiguration(listenerEle, parserContext, containerDef.getPropertyValues());

		return containerDef;
	}
