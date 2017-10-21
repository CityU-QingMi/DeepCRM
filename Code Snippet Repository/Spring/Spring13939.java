	private static HttpHeaders toHttpHeaders(HttpFields httpFields) {
		HttpHeaders responseHeaders = new HttpHeaders();
		Enumeration<String> names = httpFields.getFieldNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			Enumeration<String> values = httpFields.getValues(name);
			while (values.hasMoreElements()) {
				String value = values.nextElement();
				responseHeaders.add(name, value);
			}
		}
		return responseHeaders;
	}
