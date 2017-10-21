	@Override
	public URL getResource(String requestedPath) {
		if (this.overrides.containsKey(requestedPath)) {
			String overriddenPath = this.overrides.get(requestedPath);
			return (overriddenPath != null ? super.getResource(overriddenPath) : null);
		}
		else {
			return super.getResource(requestedPath);
		}
	}
