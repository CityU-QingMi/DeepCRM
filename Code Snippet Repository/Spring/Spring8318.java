	private WebConnection createConnection(WebClient webClient, WebConnection defaultConnection) {
		WebConnection connection = new MockMvcWebConnection(this.mockMvc, webClient, this.contextPath);
		if (this.alwaysUseMockMvc) {
			return connection;
		}
		List<DelegateWebConnection> delegates = new ArrayList<>(this.requestMatchers.size());
		for (WebRequestMatcher matcher : this.requestMatchers) {
			delegates.add(new DelegateWebConnection(matcher, connection));
		}
		return new DelegatingWebConnection(defaultConnection, delegates);
	}
