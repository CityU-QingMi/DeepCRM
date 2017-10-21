	@Override
	protected final MockHttpServletRequest createServletRequest(ServletContext servletContext) {

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest(servletContext);
		this.files.stream().forEach(request::addFile);
		this.parts.values().stream().flatMap(Collection::stream).forEach(request::addPart);

		if (!this.parts.isEmpty()) {
			new StandardMultipartHttpServletRequest(request)
					.getMultiFileMap().values().stream().flatMap(Collection::stream)
					.forEach(request::addFile);
		}

		return request;
	}
