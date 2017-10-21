	protected String getTemplate(String path) throws IOException {
		Resource resource = getResource(path);
		if (resource == null) {
			throw new IllegalStateException("Template resource [" + path + "] not found");
		}
		InputStreamReader reader = (this.charset != null ?
				new InputStreamReader(resource.getInputStream(), this.charset) :
				new InputStreamReader(resource.getInputStream()));
		return FileCopyUtils.copyToString(reader);
	}
