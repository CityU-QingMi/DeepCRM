	private List<NameValuePair> responseHeaders() {
		Collection<String> headerNames = this.response.getHeaderNames();
		List<NameValuePair> responseHeaders = new ArrayList<>(headerNames.size());
		for (String headerName : headerNames) {
			List<Object> headerValues = this.response.getHeaderValues(headerName);
			for (Object value : headerValues) {
				responseHeaders.add(new NameValuePair(headerName, String.valueOf(value)));
			}
		}
		String location = this.response.getRedirectedUrl();
		if (location != null) {
			responseHeaders.add(new NameValuePair("Location", location));
		}
		return responseHeaders;
	}
