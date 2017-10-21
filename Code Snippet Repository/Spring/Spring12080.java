	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException {

		checkRequest(request);

		String lookupPath = this.urlPathHelper.getLookupPathForRequest(request);
		if (logger.isDebugEnabled()) {
			logger.debug("Looking up cache seconds for [" + lookupPath + "]");
		}

		CacheControl cacheControl = lookupCacheControl(lookupPath);
		Integer cacheSeconds = lookupCacheSeconds(lookupPath);
		if (cacheControl != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Applying CacheControl to [" + lookupPath + "]");
			}
			applyCacheControl(response, cacheControl);
		}
		else if (cacheSeconds != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Applying CacheControl to [" + lookupPath + "]");
			}
			applyCacheSeconds(response, cacheSeconds);
		}
		else {
			if (logger.isDebugEnabled()) {
				logger.debug("Applying default cache seconds to [" + lookupPath + "]");
			}
			prepareResponse(response);
		}

		return true;
	}
