	public final Mono<String> getForUriString(String uriString, ServerWebExchange exchange) {
		if (logger.isTraceEnabled()) {
			logger.trace("Getting resource URL for request URL \"" + uriString + "\"");
		}
		ServerHttpRequest request = exchange.getRequest();
		int queryIndex = getQueryIndex(uriString);
		String lookupPath = uriString.substring(0, queryIndex);
		String query = uriString.substring(queryIndex);
		PathContainer parsedLookupPath = PathContainer.parsePath(lookupPath);
		if (logger.isTraceEnabled()) {
			logger.trace("Getting resource URL for lookup path \"" + lookupPath + "\"");
		}
		return resolveResourceUrl(parsedLookupPath).map(resolvedPath ->
				request.getPath().contextPath().value() + resolvedPath + query);
	}
