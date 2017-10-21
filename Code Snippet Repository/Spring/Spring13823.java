	public static RuntimeBeanReference registerHandshakeHandler(
			Element element, ParserContext context, @Nullable Object source) {

		RuntimeBeanReference handlerRef;
		Element handlerElem = DomUtils.getChildElementByTagName(element, "handshake-handler");
		if (handlerElem != null) {
			handlerRef = new RuntimeBeanReference(handlerElem.getAttribute("ref"));
		}
		else {
			RootBeanDefinition defaultHandlerDef = new RootBeanDefinition(DefaultHandshakeHandler.class);
			defaultHandlerDef.setSource(source);
			defaultHandlerDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			String handlerName = context.getReaderContext().registerWithGeneratedName(defaultHandlerDef);
			handlerRef = new RuntimeBeanReference(handlerName);
		}
		return handlerRef;
	}
