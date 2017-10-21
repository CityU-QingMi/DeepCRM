	private Bootstrap getBootstrap(URI uri) {
		boolean isSecure = (uri.getPort() == 443 || "https".equalsIgnoreCase(uri.getScheme()));
		if (isSecure) {
			return buildBootstrap(uri, true);
		}
		else {
			Bootstrap bootstrap = this.bootstrap;
			if (bootstrap == null) {
				bootstrap = buildBootstrap(uri, false);
				this.bootstrap = bootstrap;
			}
			return bootstrap;
		}
	}
