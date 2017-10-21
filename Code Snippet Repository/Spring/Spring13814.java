	private ManagedMap<String, Object> registerHandlerMapping(Element element,
			ParserContext context, @Nullable Object source) {

		RootBeanDefinition handlerMappingDef = new RootBeanDefinition(WebSocketHandlerMapping.class);

		String orderAttribute = element.getAttribute("order");
		int order = orderAttribute.isEmpty() ? DEFAULT_MAPPING_ORDER : Integer.valueOf(orderAttribute);
		handlerMappingDef.getPropertyValues().add("order", order);

		String pathHelper = element.getAttribute("path-helper");
		if (StringUtils.hasText(pathHelper)) {
			handlerMappingDef.getPropertyValues().add("urlPathHelper", new RuntimeBeanReference(pathHelper));
		}

		ManagedMap<String, Object> urlMap = new ManagedMap<>();
		urlMap.setSource(source);
		handlerMappingDef.getPropertyValues().add("urlMap", urlMap);

		registerBeanDef(handlerMappingDef, context, source);
		return urlMap;
	}
