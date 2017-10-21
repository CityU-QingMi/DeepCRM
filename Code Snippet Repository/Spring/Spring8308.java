	private void contextPath(MockHttpServletRequest request, UriComponents uriComponents) {
		if (this.contextPath == null) {
			List<String> pathSegments = uriComponents.getPathSegments();
			if (pathSegments.isEmpty()) {
				request.setContextPath("");
			}
			else {
				request.setContextPath("/" + pathSegments.get(0));
			}
		}
		else {
			String path = uriComponents.getPath();
			Assert.isTrue(path != null && path.startsWith(this.contextPath),
					() -> "\"" + uriComponents.getPath() +
							"\" should start with context path \"" + this.contextPath + "\"");
			request.setContextPath(this.contextPath);
		}
	}
