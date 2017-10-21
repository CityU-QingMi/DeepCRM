	@Nullable
	protected Object lookupHandler(PathContainer lookupPath, ServerWebExchange exchange)
			throws Exception {

		return this.handlerMap.entrySet().stream()
				.filter(entry -> entry.getKey().matches(lookupPath))
				.sorted((entry1, entry2) ->
						PathPattern.SPECIFICITY_COMPARATOR.compare(entry1.getKey(), entry2.getKey()))
				.findFirst()
				.map(entry -> {
					PathPattern pattern = entry.getKey();
					if (logger.isDebugEnabled()) {
						logger.debug("Matching pattern for request [" + lookupPath + "] is " + pattern);
					}
					PathContainer pathWithinMapping = pattern.extractPathWithinPattern(lookupPath);
					return handleMatch(entry.getValue(), pattern, pathWithinMapping, exchange);
				})
				.orElse(null);
	}
