	@Override
	protected RootBeanDefinition createContainer(Element containerEle, Element listenerEle, ParserContext parserContext,
			PropertyValues commonContainerProperties, PropertyValues specificContainerProperties) {

		RootBeanDefinition containerDef = new RootBeanDefinition();
		containerDef.setSource(parserContext.extractSource(containerEle));
		containerDef.setBeanClassName("org.springframework.jms.listener.endpoint.JmsMessageEndpointManager");
		containerDef.getPropertyValues().addPropertyValues(specificContainerProperties);

		RootBeanDefinition configDef = new RootBeanDefinition();
		configDef.setSource(parserContext.extractSource(containerEle));
		configDef.setBeanClassName("org.springframework.jms.listener.endpoint.JmsActivationSpecConfig");
		configDef.getPropertyValues().addPropertyValues(commonContainerProperties);
		parseListenerConfiguration(listenerEle, parserContext, configDef.getPropertyValues());

		containerDef.getPropertyValues().add("activationSpecConfig", configDef);

		return containerDef;
	}
