	private void registerAnnotationMethodMessageHandler(Element messageBrokerElement,
			RuntimeBeanReference inChannel, RuntimeBeanReference outChannel,
			RuntimeBeanReference converter, RuntimeBeanReference messagingTemplate,
			ParserContext context, @Nullable Object source) {

		ConstructorArgumentValues cavs = new ConstructorArgumentValues();
		cavs.addIndexedArgumentValue(0, inChannel);
		cavs.addIndexedArgumentValue(1, outChannel);
		cavs.addIndexedArgumentValue(2, messagingTemplate);

		MutablePropertyValues values = new MutablePropertyValues();
		String prefixAttribute = messageBrokerElement.getAttribute("application-destination-prefix");
		values.add("destinationPrefixes", Arrays.asList(StringUtils.tokenizeToStringArray(prefixAttribute, ",")));
		values.add("messageConverter", converter);

		RootBeanDefinition beanDef = new RootBeanDefinition(WebSocketAnnotationMethodMessageHandler.class, cavs, values);
		if (messageBrokerElement.hasAttribute("path-matcher")) {
			String pathMatcherRef = messageBrokerElement.getAttribute("path-matcher");
			beanDef.getPropertyValues().add("pathMatcher", new RuntimeBeanReference(pathMatcherRef));
		}

		RuntimeBeanReference validatorRef = getValidator(messageBrokerElement, source, context);
		if (validatorRef != null) {
			beanDef.getPropertyValues().add("validator", validatorRef);
		}

		Element resolversElement = DomUtils.getChildElementByTagName(messageBrokerElement, "argument-resolvers");
		if (resolversElement != null) {
			values.add("customArgumentResolvers", extractBeanSubElements(resolversElement, context));
		}

		Element handlersElement = DomUtils.getChildElementByTagName(messageBrokerElement, "return-value-handlers");
		if (handlersElement != null) {
			values.add("customReturnValueHandlers", extractBeanSubElements(handlersElement, context));
		}

		registerBeanDef(beanDef, context, source);
	}
