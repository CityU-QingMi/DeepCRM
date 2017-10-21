	private RuntimeBeanReference registerUserDestHandler(Element brokerElem,
			RuntimeBeanReference userRegistry, RuntimeBeanReference inChannel,
			RuntimeBeanReference brokerChannel, ParserContext context, @Nullable Object source) {

		Object userDestResolver = registerUserDestResolver(brokerElem, userRegistry, context, source);

		RootBeanDefinition beanDef = new RootBeanDefinition(UserDestinationMessageHandler.class);
		beanDef.getConstructorArgumentValues().addIndexedArgumentValue(0, inChannel);
		beanDef.getConstructorArgumentValues().addIndexedArgumentValue(1, brokerChannel);
		beanDef.getConstructorArgumentValues().addIndexedArgumentValue(2, userDestResolver);

		Element relayElement = DomUtils.getChildElementByTagName(brokerElem, "stomp-broker-relay");
		if (relayElement != null && relayElement.hasAttribute("user-destination-broadcast")) {
			String destination = relayElement.getAttribute("user-destination-broadcast");
			beanDef.getPropertyValues().add("broadcastDestination", destination);
		}

		String beanName = registerBeanDef(beanDef, context, source);
		return new RuntimeBeanReference(beanName);
	}
