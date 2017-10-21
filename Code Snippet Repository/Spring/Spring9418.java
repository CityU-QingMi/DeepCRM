	@Override
	public URI getURI() {
		try {
			StringBuffer url = this.servletRequest.getRequestURL();
			String query = this.servletRequest.getQueryString();
			if (StringUtils.hasText(query)) {
				url.append('?').append(query);
			}
			return new URI(url.toString());
		}
		catch (URISyntaxException ex) {
			throw new IllegalStateException("Could not get HttpServletRequest URI: " + ex.getMessage(), ex);
		}
	}
