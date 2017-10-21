	private Mono<String> resolveResourceUrl(PathContainer lookupPath) {
		return this.handlerMap.entrySet().stream()
				.filter(entry -> entry.getKey().matches(lookupPath))
				.sorted((entry1, entry2) ->
						PathPattern.SPECIFICITY_COMPARATOR.compare(entry1.getKey(), entry2.getKey()))
				.findFirst()
				.map(entry -> {
					PathContainer path = entry.getKey().extractPathWithinPattern(lookupPath);
					int endIndex = lookupPath.elements().size() - path.elements().size();
					PathContainer mapping = lookupPath.subPath(0, endIndex);
					if (logger.isTraceEnabled()) {
						logger.trace("Invoking ResourceResolverChain for URL pattern " +
								"\"" + entry.getKey() + "\"");
					}
					ResourceWebHandler handler = entry.getValue();
					List<ResourceResolver> resolvers = handler.getResourceResolvers();
					ResourceResolverChain chain = new DefaultResourceResolverChain(resolvers);
					return chain.resolveUrlPath(path.value(), handler.getLocations())
							.map(resolvedPath -> {
								if (logger.isTraceEnabled()) {
									logger.trace("Resolved public resource URL path \"" + resolvedPath + "\"");
								}
								return mapping.value() + resolvedPath;
							});

				})
				.orElse(Mono.empty());
	}
