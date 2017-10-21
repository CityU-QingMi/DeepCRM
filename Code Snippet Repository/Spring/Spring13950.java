	@Nullable
	private HttpHeaders getHttpRequestHeaders(@Nullable HttpHeaders webSocketHttpHeaders) {
		if (getHttpHeaderNames() == null || webSocketHttpHeaders == null) {
			return webSocketHttpHeaders;
		}
		else {
			HttpHeaders httpHeaders = new HttpHeaders();
			for (String name : getHttpHeaderNames()) {
				List<String> values = webSocketHttpHeaders.get(name);
				if (values != null) {
					httpHeaders.put(name, values);
				}
			}
			return httpHeaders;
		}
	}
