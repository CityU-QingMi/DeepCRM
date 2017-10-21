	private LineOutput processLine(LineInfo info, HttpServletRequest request,
			Resource resource, ResourceTransformerChain transformerChain) {

		if (!info.isLink()) {
			return new LineOutput(info.getLine(), null);
		}

		Resource appCacheResource = transformerChain.getResolverChain()
				.resolveResource(null, info.getLine(), Collections.singletonList(resource));

		String path = info.getLine();
		String absolutePath = toAbsolutePath(path, request);
		String newPath = resolveUrlPath(absolutePath, request, resource, transformerChain);

		if (logger.isTraceEnabled()) {
			if (newPath != null && !newPath.equals(path)) {
				logger.trace("Link modified: " + path + " (original: " + path + ")");
			}
			else {
				logger.trace("Link not modified: " + path);
			}
		}

		return new LineOutput((newPath != null ? newPath : path), appCacheResource);
	}
