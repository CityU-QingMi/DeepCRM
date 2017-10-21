	private List<String> getVaryRequestHeadersToAdd(HttpHeaders responseHeaders, HttpHeaders entityHeaders) {
		List<String> entityHeadersVary = entityHeaders.getVary();
		List<String> vary = responseHeaders.get(HttpHeaders.VARY);
		if (vary != null) {
			List<String> result = new ArrayList<>(entityHeadersVary);
			for (String header : vary) {
				for (String existing : StringUtils.tokenizeToStringArray(header, ",")) {
					if ("*".equals(existing)) {
						return Collections.emptyList();
					}
					for (String value : entityHeadersVary) {
						if (value.equalsIgnoreCase(existing)) {
							result.remove(value);
						}
					}
				}
			}
			return result;
		}
		return entityHeadersVary;
	}
