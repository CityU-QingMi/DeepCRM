	protected final HttpHeaders getRequestHeaders(MockHttpServletRequest request) {
		HttpHeaders headers = new HttpHeaders();
		Enumeration<?> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			Enumeration<String> values = request.getHeaders(name);
			while (values.hasMoreElements()) {
				headers.add(name, values.nextElement());
			}
		}
		return headers;
	}
