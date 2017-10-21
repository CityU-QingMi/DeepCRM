	protected String computeKey(@Nullable ServerWebExchange exchange, String requestPath) {
		StringBuilder key = new StringBuilder(RESOLVED_RESOURCE_CACHE_KEY_PREFIX);
		key.append(requestPath);
		if (exchange != null) {
			String encoding = exchange.getRequest().getHeaders().getFirst("Accept-Encoding");
			if (encoding != null && encoding.contains("gzip")) {
				key.append("+encoding=gzip");
			}
		}
		return key.toString();
	}
