	private static AbstractBeanDefinition parseComponentElement(Element element) {
		BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(ComponentFactoryBean.class);
		factory.addPropertyValue("parent", parseComponent(element));

		List<Element> childElements = DomUtils.getChildElementsByTagName(element, "component");
		if (!CollectionUtils.isEmpty(childElements)) {
			parseChildComponents(childElements, factory);
		}

		return factory.getBeanDefinition();
	}
