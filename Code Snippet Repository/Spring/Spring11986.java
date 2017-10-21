	private CacheControl parseCacheControl(Element element) {
		CacheControl cacheControl = CacheControl.empty();
		if ("true".equals(element.getAttribute("no-cache"))) {
			cacheControl = CacheControl.noCache();
		}
		else if ("true".equals(element.getAttribute("no-store"))) {
			cacheControl = CacheControl.noStore();
		}
		else if (element.hasAttribute("max-age")) {
			cacheControl = CacheControl.maxAge(Long.parseLong(element.getAttribute("max-age")), TimeUnit.SECONDS);
		}
		if ("true".equals(element.getAttribute("must-revalidate"))) {
			cacheControl = cacheControl.mustRevalidate();
		}
		if ("true".equals(element.getAttribute("no-transform"))) {
			cacheControl = cacheControl.noTransform();
		}
		if ("true".equals(element.getAttribute("cache-public"))) {
			cacheControl = cacheControl.cachePublic();
		}
		if ("true".equals(element.getAttribute("cache-private"))) {
			cacheControl = cacheControl.cachePrivate();
		}
		if ("true".equals(element.getAttribute("proxy-revalidate"))) {
			cacheControl = cacheControl.proxyRevalidate();
		}
		if (element.hasAttribute("s-maxage")) {
			cacheControl = cacheControl.sMaxAge(Long.parseLong(element.getAttribute("s-maxage")), TimeUnit.SECONDS);
		}
		if (element.hasAttribute("stale-while-revalidate")) {
			cacheControl = cacheControl.staleWhileRevalidate(
					Long.parseLong(element.getAttribute("stale-while-revalidate")), TimeUnit.SECONDS);
		}
		if (element.hasAttribute("stale-if-error")) {
			cacheControl = cacheControl.staleIfError(
					Long.parseLong(element.getAttribute("stale-if-error")), TimeUnit.SECONDS);
		}
		return cacheControl;
	}
