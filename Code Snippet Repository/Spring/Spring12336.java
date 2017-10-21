	private static String prependForwardedPrefix(HttpServletRequest request, String path) {
		String prefix = null;
		Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			if ("X-Forwarded-Prefix".equalsIgnoreCase(name)) {
				prefix = request.getHeader(name);
			}
		}
		if (prefix != null) {
			path = prefix + path;
		}
		return path;
	}
