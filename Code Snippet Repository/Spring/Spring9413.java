	private static PathContainer initContextPath(PathContainer path, @Nullable String contextPath) {
		if (!StringUtils.hasText(contextPath) || "/".equals(contextPath)) {
			return PathContainer.parsePath("");
		}

		validateContextPath(path.value(), contextPath);

		int length = contextPath.length();
		int counter = 0;

		for (int i=0; i < path.elements().size(); i++) {
			PathContainer.Element element = path.elements().get(i);
			counter += element.value().length();
			if (length == counter) {
				return path.subPath(0, i + 1);
			}
		}

		// Should not happen..
		throw new IllegalStateException("Failed to initialize contextPath '" + contextPath + "'" +
				" for requestPath '" + path.value() + "'");
	}
