	private static void validateContextPath(String fullPath, String contextPath) {
		int length = contextPath.length();
		if (contextPath.charAt(0) != '/' || contextPath.charAt(length - 1) == '/') {
			throw new IllegalArgumentException("Invalid contextPath: '" + contextPath + "': " +
					"must start with '/' and not end with '/'");
		}
		if (!fullPath.startsWith(contextPath)) {
			throw new IllegalArgumentException("Invalid contextPath '" + contextPath + "': " +
					"must match the start of requestPath: '" + fullPath + "'");
		}
		if (fullPath.length() > length && fullPath.charAt(length) != '/') {
			throw new IllegalArgumentException("Invalid contextPath '" + contextPath + "': " +
					"must match to full path segments for requestPath: '" + fullPath + "'");
		}
	}
