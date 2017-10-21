		@Nullable
		private static String getForwardedPrefix(HttpServletRequest request) {
			String prefix = null;
			Enumeration<String> names = request.getHeaderNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				if ("X-Forwarded-Prefix".equalsIgnoreCase(name)) {
					prefix = request.getHeader(name);
				}
			}
			if (prefix != null) {
				while (prefix.endsWith("/")) {
					prefix = prefix.substring(0, prefix.length() - 1);
				}
			}
			return prefix;
		}
