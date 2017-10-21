	@Override
	protected void copyToUriComponentsBuilder(UriComponentsBuilder builder) {
		if (getScheme() != null) {
			builder.scheme(getScheme());
		}
		if (getSchemeSpecificPart() != null) {
			builder.schemeSpecificPart(getSchemeSpecificPart());
		}
		if (getFragment() != null) {
			builder.fragment(getFragment());
		}
	}
