	@Nullable
	protected Resource getResource(HttpServletRequest request) throws IOException {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		if (path == null) {
			throw new IllegalStateException("Required request attribute '" +
					HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE + "' is not set");
		}
		path = processPath(path);
		if (!StringUtils.hasText(path) || isInvalidPath(path)) {
			if (logger.isTraceEnabled()) {
				logger.trace("Ignoring invalid resource path [" + path + "]");
			}
			return null;
		}
		if (path.contains("%")) {
			try {
				// Use URLDecoder (vs UriUtils) to preserve potentially decoded UTF-8 chars
				if (isInvalidPath(URLDecoder.decode(path, "UTF-8"))) {
					if (logger.isTraceEnabled()) {
						logger.trace("Ignoring invalid resource path with escape sequences [" + path + "].");
					}
					return null;
				}
			}
			catch (IllegalArgumentException ex) {
				// ignore
			}
		}
		ResourceResolverChain resolveChain = new DefaultResourceResolverChain(getResourceResolvers());
		Resource resource = resolveChain.resolveResource(request, path, getLocations());
		if (resource == null || getResourceTransformers().isEmpty()) {
			return resource;
		}
		ResourceTransformerChain transformChain =
				new DefaultResourceTransformerChain(resolveChain, getResourceTransformers());
		resource = transformChain.transform(request, resource);
		return resource;
	}
