	private URI getUriToUse() {
		if (this.uriPath == null) {
			return this.uri;
		}
		try {
			return new URI(this.uri.getScheme(), this.uri.getUserInfo(), uri.getHost(), uri.getPort(),
					uriPath, uri.getQuery(), uri.getFragment());
		}
		catch (URISyntaxException ex) {
			throw new IllegalStateException("Invalid URI path: \"" + this.uriPath + "\"");
		}
	}
