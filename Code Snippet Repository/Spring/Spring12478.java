	@Nullable
	protected Resource getResource(String location) {
		if (this.resourceLoaderPaths != null) {
			for (String path : this.resourceLoaderPaths) {
				Resource resource = obtainApplicationContext().getResource(path + location);
				if (resource.exists()) {
					return resource;
				}
			}
		}
		return null;
	}
