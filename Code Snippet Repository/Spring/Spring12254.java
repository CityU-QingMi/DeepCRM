	@Override
	@Nullable
	public Resource resolveResource(@Nullable HttpServletRequest request, String requestPath,
			List<? extends Resource> locations, ResourceResolverChain chain) {

		if (logger.isTraceEnabled()) {
			logger.trace("Resolving resource for request path \"" + requestPath + "\"");
		}
		return resolveResourceInternal(request, requestPath, locations, chain);
	}
