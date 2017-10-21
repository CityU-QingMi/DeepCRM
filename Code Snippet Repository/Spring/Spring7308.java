	private StompHeaders(Map<String, List<String>> headers, boolean readOnly) {
		Assert.notNull(headers, "'headers' must not be null");
		if (readOnly) {
			Map<String, List<String>> map = new LinkedMultiValueMap<>(headers.size());
			headers.forEach((key, value) -> map.put(key, Collections.unmodifiableList(value)));
			this.headers = Collections.unmodifiableMap(map);
		}
		else {
			this.headers = headers;
		}
	}
