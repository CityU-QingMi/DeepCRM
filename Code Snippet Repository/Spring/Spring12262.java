	protected String computeKey(@Nullable HttpServletRequest request, String requestPath) {
		StringBuilder key = new StringBuilder(RESOLVED_RESOURCE_CACHE_KEY_PREFIX);
		key.append(requestPath);
		if (request != null) {
			String encoding = request.getHeader("Accept-Encoding");
			if (encoding != null && encoding.contains("gzip")) {
				key.append("+encoding=gzip");
			}
		}
		return key.toString();
	}
