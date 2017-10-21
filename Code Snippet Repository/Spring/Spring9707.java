	protected boolean isEligibleForEtag(HttpServletRequest request, HttpServletResponse response,
			int responseStatusCode, InputStream inputStream) {

		String method = request.getMethod();
		if (responseStatusCode >= 200 && responseStatusCode < 300
				&& HttpMethod.GET.matches(method)) {

			String cacheControl = response.getHeader(HEADER_CACHE_CONTROL);
			if (cacheControl == null || !cacheControl.contains(DIRECTIVE_NO_STORE)) {
				return true;
			}
		}
		return false;
	}
