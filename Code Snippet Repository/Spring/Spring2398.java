	@Override
	public Enumeration<URL> getResources(String requestedPath) throws IOException {
		if (this.overrides.containsKey(requestedPath)) {
			String overriddenLocation = this.overrides.get(requestedPath);
			return (overriddenLocation != null ?
					super.getResources(overriddenLocation) : EMPTY_URL_ENUMERATION);
		}
		else {
			return super.getResources(requestedPath);
		}
	}
