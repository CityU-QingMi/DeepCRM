	private static URI initUri(HttpServletRequest request) {
		Assert.notNull(request, "'request' must not be null");
		try {
			StringBuffer url = request.getRequestURL();
			String query = request.getQueryString();
			if (StringUtils.hasText(query)) {
				url.append('?').append(query);
			}
			return new URI(url.toString());
		}
		catch (URISyntaxException ex) {
			throw new IllegalStateException("Could not get URI: " + ex.getMessage(), ex);
		}
	}
