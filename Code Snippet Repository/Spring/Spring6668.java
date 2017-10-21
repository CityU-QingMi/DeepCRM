	@Override
	protected RootBeanDefinition createContainerFactory(String factoryId, Element containerEle, ParserContext parserContext,
			PropertyValues commonContainerProperties, PropertyValues specificContainerProperties) {

		RootBeanDefinition factoryDef = new RootBeanDefinition();

		String containerType = containerEle.getAttribute(CONTAINER_TYPE_ATTRIBUTE);
		String containerClass = containerEle.getAttribute(CONTAINER_CLASS_ATTRIBUTE);
		if (!"".equals(containerClass)) {
			return null; // Not supported
		}
		else if ("".equals(containerType) || containerType.startsWith("default")) {
			factoryDef.setBeanClassName("org.springframework.jms.config.DefaultJmsListenerContainerFactory");
		}
		else if (containerType.startsWith("simple")) {
			factoryDef.setBeanClassName("org.springframework.jms.config.SimpleJmsListenerContainerFactory");
		}

		factoryDef.getPropertyValues().addPropertyValues(commonContainerProperties);
		factoryDef.getPropertyValues().addPropertyValues(specificContainerProperties);

		return factoryDef;
	}
