	private RuntimeBeanReference registerUserRegistry(Element element, ParserContext context, @Nullable Object source) {

		Element relayElement = DomUtils.getChildElementByTagName(element, "stomp-broker-relay");
		boolean multiServer = (relayElement != null && relayElement.hasAttribute("user-registry-broadcast"));

		if (multiServer) {
			RootBeanDefinition localRegistryBeanDef = new RootBeanDefinition(DefaultSimpUserRegistry.class);
			RootBeanDefinition beanDef = new RootBeanDefinition(MultiServerUserRegistry.class);
			beanDef.getConstructorArgumentValues().addIndexedArgumentValue(0, localRegistryBeanDef);
			String beanName = registerBeanDef(beanDef, context, source);
			return new RuntimeBeanReference(beanName);
		}
		else {
			RootBeanDefinition beanDef = new RootBeanDefinition(DefaultSimpUserRegistry.class);
			String beanName = registerBeanDef(beanDef, context, source);
			return new RuntimeBeanReference(beanName);
		}
	}
