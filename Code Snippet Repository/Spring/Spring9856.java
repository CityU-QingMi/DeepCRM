	@Override
	protected void copyToUriComponentsBuilder(UriComponentsBuilder builder) {
		if (getScheme() != null) {
			builder.scheme(getScheme());
		}
		if (getUserInfo() != null) {
			builder.userInfo(getUserInfo());
		}
		if (getHost() != null) {
			builder.host(getHost());
		}
		// Avoid parsing the port, may have URI variable..
		if (this.port != null) {
			builder.port(this.port);
		}
		this.path.copyToUriComponentsBuilder(builder);
		if (!getQueryParams().isEmpty()) {
			builder.queryParams(getQueryParams());
		}
		if (getFragment() != null) {
			builder.fragment(getFragment());
		}
	}
