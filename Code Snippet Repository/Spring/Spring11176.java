	@Override
	public String addVersion(String path, String version) {
		if (path.startsWith(".")) {
			return path;
		}
		else if (this.prefix.endsWith("/") || path.startsWith("/")) {
			return this.prefix + path;
		}
		else {
			return this.prefix + '/' + path;
		}
	}
