	private final String getAbsolutePathRelativeToContext(String relativeUrlPath) {
		String path = relativeUrlPath;

		if (!path.startsWith("/")) {
			String uri = (String) request
					.getAttribute("javax.servlet.include.servlet_path");
			if (uri == null)
				uri = ((HttpServletRequest) request).getServletPath();
			String baseURI = uri.substring(0, uri.lastIndexOf('/'));
			path = baseURI + '/' + path;
		}

		return path;
	}
