	@Override
	@Nullable
	public BeanDefinition parse(Element element, ParserContext context) {
		Object source = context.extractSource(element);
		CompositeComponentDefinition compDefinition = new CompositeComponentDefinition(element.getTagName(), source);
		context.pushContainingComponent(compDefinition);

		String orderAttribute = element.getAttribute("order");
		int order = orderAttribute.isEmpty() ? DEFAULT_MAPPING_ORDER : Integer.valueOf(orderAttribute);

		RootBeanDefinition handlerMappingDef = new RootBeanDefinition(WebSocketHandlerMapping.class);
		handlerMappingDef.setSource(source);
		handlerMappingDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		handlerMappingDef.getPropertyValues().add("order", order);
		String handlerMappingName = context.getReaderContext().registerWithGeneratedName(handlerMappingDef);

		RuntimeBeanReference sockJsService = WebSocketNamespaceUtils.registerSockJsService(
				element, SOCK_JS_SCHEDULER_NAME, context, source);

		HandlerMappingStrategy strategy;
		if (sockJsService != null) {
			strategy = new SockJsHandlerMappingStrategy(sockJsService);
		}
		else {
			RuntimeBeanReference handshakeHandler = WebSocketNamespaceUtils.registerHandshakeHandler(element, context, source);
			Element interceptorsElement = DomUtils.getChildElementByTagName(element, "handshake-interceptors");
			ManagedList<? super Object> interceptors = WebSocketNamespaceUtils.parseBeanSubElements(interceptorsElement, context);
			String allowedOriginsAttribute = element.getAttribute("allowed-origins");
			List<String> allowedOrigins = Arrays.asList(StringUtils.tokenizeToStringArray(allowedOriginsAttribute, ","));
			interceptors.add(new OriginHandshakeInterceptor(allowedOrigins));
			strategy = new WebSocketHandlerMappingStrategy(handshakeHandler, interceptors);
		}

		ManagedMap<String, Object> urlMap = new ManagedMap<>();
		urlMap.setSource(source);
		for (Element mappingElement : DomUtils.getChildElementsByTagName(element, "mapping")) {
			strategy.addMapping(mappingElement, urlMap, context);
		}
		handlerMappingDef.getPropertyValues().add("urlMap", urlMap);

		context.registerComponent(new BeanComponentDefinition(handlerMappingDef, handlerMappingName));
		context.popAndRegisterContainingComponent();
		return null;
	}
