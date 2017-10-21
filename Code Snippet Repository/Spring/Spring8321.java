	private void updatePathRequestProperties(MockHttpServletRequest request, String requestUri) {
		if (!requestUri.startsWith(this.contextPath)) {
			throw new IllegalArgumentException(
					"Request URI [" + requestUri + "] does not start with context path [" + this.contextPath + "]");
		}
		request.setContextPath(this.contextPath);
		request.setServletPath(this.servletPath);

		if ("".equals(this.pathInfo)) {
			if (!requestUri.startsWith(this.contextPath + this.servletPath)) {
				throw new IllegalArgumentException(
						"Invalid servlet path [" + this.servletPath + "] for request URI [" + requestUri + "]");
			}
			String extraPath = requestUri.substring(this.contextPath.length() + this.servletPath.length());
			this.pathInfo = (StringUtils.hasText(extraPath) ? extraPath : null);
		}
		request.setPathInfo(this.pathInfo);
	}
