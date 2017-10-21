	@Nullable
	public String getHeaderValue() {
		StringBuilder ccValue = new StringBuilder();
		if (this.maxAge != -1) {
			appendDirective(ccValue, "max-age=" + Long.toString(this.maxAge));
		}
		if (this.noCache) {
			appendDirective(ccValue, "no-cache");
		}
		if (this.noStore) {
			appendDirective(ccValue, "no-store");
		}
		if (this.mustRevalidate) {
			appendDirective(ccValue, "must-revalidate");
		}
		if (this.noTransform) {
			appendDirective(ccValue, "no-transform");
		}
		if (this.cachePublic) {
			appendDirective(ccValue, "public");
		}
		if (this.cachePrivate) {
			appendDirective(ccValue, "private");
		}
		if (this.proxyRevalidate) {
			appendDirective(ccValue, "proxy-revalidate");
		}
		if (this.sMaxAge != -1) {
			appendDirective(ccValue, "s-maxage=" + Long.toString(this.sMaxAge));
		}
		if (this.staleIfError != -1) {
			appendDirective(ccValue, "stale-if-error=" + Long.toString(this.staleIfError));
		}
		if (this.staleWhileRevalidate != -1) {
			appendDirective(ccValue, "stale-while-revalidate=" + Long.toString(this.staleWhileRevalidate));
		}

		String ccHeaderValue = ccValue.toString();
		return (StringUtils.hasText(ccHeaderValue) ? ccHeaderValue : null);
	}
