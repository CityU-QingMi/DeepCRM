	protected StringBuilder appendCurrentRequestQuery(String targetUrl, ServerHttpRequest request) {
		String query = request.getURI().getRawQuery();
		if (!StringUtils.hasText(query)) {
			return new StringBuilder(targetUrl);
		}

		int index = targetUrl.indexOf("#");
		String fragment = (index > -1 ? targetUrl.substring(index) : null);

		StringBuilder result = new StringBuilder();
		result.append(index != -1 ? targetUrl.substring(0, index) : targetUrl);
		result.append(targetUrl.indexOf('?') < 0 ? '?' : '&').append(query);

		if (fragment != null) {
			result.append(fragment);
		}

		return result;
	}
