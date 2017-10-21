	@Nullable
	private static String getForwardedPrefix(HttpHeaders headers) {
		String prefix = headers.getFirst("X-Forwarded-Prefix");
		if (prefix != null) {
			while (prefix.endsWith("/")) {
				prefix = prefix.substring(0, prefix.length() - 1);
			}
		}
		return prefix;
	}
