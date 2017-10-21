	private RuntimeBeanReference registerRequestHandler(Element element, RuntimeBeanReference subProtoHandler,
			ParserContext context, @Nullable Object source) {

		RootBeanDefinition beanDef;

		RuntimeBeanReference sockJsService = WebSocketNamespaceUtils.registerSockJsService(
				element, SCHEDULER_BEAN_NAME, context, source);

		if (sockJsService != null) {
			ConstructorArgumentValues cavs = new ConstructorArgumentValues();
			cavs.addIndexedArgumentValue(0, sockJsService);
			cavs.addIndexedArgumentValue(1, subProtoHandler);
			beanDef = new RootBeanDefinition(SockJsHttpRequestHandler.class, cavs, null);

			// Register alias for backwards compatibility with 4.1
			context.getRegistry().registerAlias(SCHEDULER_BEAN_NAME, SOCKJS_SCHEDULER_BEAN_NAME);
		}
		else {
			RuntimeBeanReference handshakeHandler = WebSocketNamespaceUtils.registerHandshakeHandler(element, context, source);
			Element interceptorsElement = DomUtils.getChildElementByTagName(element, "handshake-interceptors");
			ManagedList<? super Object> interceptors = WebSocketNamespaceUtils.parseBeanSubElements(interceptorsElement, context);
			String allowedOriginsAttribute = element.getAttribute("allowed-origins");
			List<String> allowedOrigins = Arrays.asList(StringUtils.tokenizeToStringArray(allowedOriginsAttribute, ","));
			interceptors.add(new OriginHandshakeInterceptor(allowedOrigins));
			ConstructorArgumentValues cavs = new ConstructorArgumentValues();
			cavs.addIndexedArgumentValue(0, subProtoHandler);
			cavs.addIndexedArgumentValue(1, handshakeHandler);
			beanDef = new RootBeanDefinition(WebSocketHttpRequestHandler.class, cavs, null);
			beanDef.getPropertyValues().add("handshakeInterceptors", interceptors);
		}
		return new RuntimeBeanReference(registerBeanDef(beanDef, context, source));
	}
