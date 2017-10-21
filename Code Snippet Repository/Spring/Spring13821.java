	private RuntimeBeanReference registerMessagingTemplate(Element element, RuntimeBeanReference brokerChannel,
			RuntimeBeanReference messageConverter, ParserContext context, @Nullable Object source) {

		ConstructorArgumentValues cavs = new ConstructorArgumentValues();
		cavs.addIndexedArgumentValue(0, brokerChannel);
		RootBeanDefinition beanDef = new RootBeanDefinition(SimpMessagingTemplate.class,cavs, null);
		if (element.hasAttribute("user-destination-prefix")) {
			beanDef.getPropertyValues().add("userDestinationPrefix", element.getAttribute("user-destination-prefix"));
		}
		beanDef.getPropertyValues().add("messageConverter", messageConverter);
		String name = MESSAGING_TEMPLATE_BEAN_NAME;
		registerBeanDefByName(name, beanDef, context, source);
		return new RuntimeBeanReference(name);
	}
