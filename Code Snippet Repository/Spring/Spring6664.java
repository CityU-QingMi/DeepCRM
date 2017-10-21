	@Override
	protected RootBeanDefinition createContainerFactory(String factoryId, Element containerEle, ParserContext parserContext,
			PropertyValues commonContainerProperties, PropertyValues specificContainerProperties) {

		RootBeanDefinition factoryDef = new RootBeanDefinition();
		factoryDef.setBeanClassName("org.springframework.jms.config.DefaultJcaListenerContainerFactory");

		factoryDef.getPropertyValues().addPropertyValues(commonContainerProperties);
		factoryDef.getPropertyValues().addPropertyValues(specificContainerProperties);

		return factoryDef;
	}
