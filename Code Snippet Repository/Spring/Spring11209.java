	protected Mono<Resource> getResource(ServerWebExchange exchange) {

		String name = HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE;
		PathContainer pathWithinHandler = exchange.getRequiredAttribute(name);
		String path = processPath(pathWithinHandler.value());
		if (!StringUtils.hasText(path) || isInvalidPath(path)) {
			if (logger.isTraceEnabled()) {
				logger.trace("Ignoring invalid resource path [" + path + "]");
			}
			return Mono.empty();
		}

		if (path.contains("%")) {
			try {
				// Use URLDecoder (vs UriUtils) to preserve potentially decoded UTF-8 chars
				if (isInvalidPath(URLDecoder.decode(path, "UTF-8"))) {
					if (logger.isTraceEnabled()) {
						logger.trace("Ignoring invalid resource path with escape sequences [" + path + "].");
					}
					return Mono.empty();
				}
			}
			catch (IllegalArgumentException ex) {
				// ignore
			}
			catch (UnsupportedEncodingException ex) {
				return Mono.error(Exceptions.propagate(ex));
			}
		}

		ResourceResolverChain resolveChain = createResolverChain();
		return resolveChain.resolveResource(exchange, path, getLocations())
				.flatMap(resource -> {
					ResourceTransformerChain transformerChain = createTransformerChain(resolveChain);
					return transformerChain.transform(exchange, resource);
				});
	}
