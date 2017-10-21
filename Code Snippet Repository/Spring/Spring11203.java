	private void detectResourceHandlers(ApplicationContext context) {
		logger.debug("Looking for resource handler mappings");

		Map<String, SimpleUrlHandlerMapping> map = context.getBeansOfType(SimpleUrlHandlerMapping.class);
		List<SimpleUrlHandlerMapping> mappings = new ArrayList<>(map.values());
		AnnotationAwareOrderComparator.sort(mappings);

		mappings.forEach(mapping -> {
			mapping.getHandlerMap().forEach((pattern, handler) -> {
				if (handler instanceof ResourceWebHandler) {
					ResourceWebHandler resourceHandler = (ResourceWebHandler) handler;
					if (logger.isDebugEnabled()) {
						logger.debug("Found resource handler mapping: URL pattern=\"" + pattern + "\", " +
								"locations=" + resourceHandler.getLocations() + ", " +
								"resolvers=" + resourceHandler.getResourceResolvers());
					}
					this.handlerMap.put(pattern, resourceHandler);
				}
			});
		});
	}
