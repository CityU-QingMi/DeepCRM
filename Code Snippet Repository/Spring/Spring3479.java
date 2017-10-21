	@Override
	public Enumeration<URL> getResources(String name) throws IOException {
		if (CandidateComponentsIndexLoader.COMPONENTS_RESOURCE_LOCATION.equals(name)) {
			if (this.resourceUrls != null) {
				return this.resourceUrls;
			}
			throw this.cause;
		}
		return super.getResources(name);
	}
