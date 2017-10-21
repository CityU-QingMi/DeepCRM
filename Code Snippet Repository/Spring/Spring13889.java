	@Override
	public List<WebSocketExtension> getSupportedExtensions(ServerHttpRequest request) {
		List<WebSocketExtension> extensions = this.extensions;
		if (extensions == null) {
			HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
			extensions = getInstalledExtensions(getContainer(servletRequest));
			this.extensions = extensions;
		}
		return extensions;
	}
