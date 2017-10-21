	private RuntimeBeanReference registerStompHandler(Element element, RuntimeBeanReference inChannel,
			RuntimeBeanReference outChannel, ParserContext context, @Nullable Object source) {

		RootBeanDefinition stompHandlerDef = new RootBeanDefinition(StompSubProtocolHandler.class);
		registerBeanDef(stompHandlerDef, context, source);

		Element errorHandlerElem = DomUtils.getChildElementByTagName(element, "stomp-error-handler");
		if (errorHandlerElem != null) {
			RuntimeBeanReference errorHandlerRef = new RuntimeBeanReference(errorHandlerElem.getAttribute("ref"));
			stompHandlerDef.getPropertyValues().add("errorHandler", errorHandlerRef);
		}

		ConstructorArgumentValues cavs = new ConstructorArgumentValues();
		cavs.addIndexedArgumentValue(0, inChannel);
		cavs.addIndexedArgumentValue(1, outChannel);

		RootBeanDefinition handlerDef = new RootBeanDefinition(SubProtocolWebSocketHandler.class, cavs, null);
		handlerDef.getPropertyValues().addPropertyValue("protocolHandlers", stompHandlerDef);
		registerBeanDefByName(WEB_SOCKET_HANDLER_BEAN_NAME, handlerDef, context, source);
		RuntimeBeanReference result = new RuntimeBeanReference(WEB_SOCKET_HANDLER_BEAN_NAME);

		Element transportElem = DomUtils.getChildElementByTagName(element, "transport");
		if (transportElem != null) {
			if (transportElem.hasAttribute("message-size")) {
				stompHandlerDef.getPropertyValues().add("messageSizeLimit", transportElem.getAttribute("message-size"));
			}
			if (transportElem.hasAttribute("send-timeout")) {
				handlerDef.getPropertyValues().add("sendTimeLimit", transportElem.getAttribute("send-timeout"));
			}
			if (transportElem.hasAttribute("send-buffer-size")) {
				handlerDef.getPropertyValues().add("sendBufferSizeLimit", transportElem.getAttribute("send-buffer-size"));
			}
			Element factoriesElement = DomUtils.getChildElementByTagName(transportElem, "decorator-factories");
			if (factoriesElement != null) {
				ManagedList<Object> factories = extractBeanSubElements(factoriesElement, context);
				RootBeanDefinition factoryBean = new RootBeanDefinition(DecoratingFactoryBean.class);
				factoryBean.getConstructorArgumentValues().addIndexedArgumentValue(0, result);
				factoryBean.getConstructorArgumentValues().addIndexedArgumentValue(1, factories);
				result = new RuntimeBeanReference(registerBeanDef(factoryBean, context, source));
			}
		}
		return result;
	}
