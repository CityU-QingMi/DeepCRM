	private void assertFilterInvoked(String requestUri, String pattern) throws Exception {
		request.setRequestURI(request.getContextPath() + requestUri);
		filter = new PatternMappingFilterProxy(delegate, pattern);
		filter.doFilter(request, response, filterChain);

		assertThat(delegate.request, equalTo((ServletRequest) request));
		assertThat(delegate.response, equalTo((ServletResponse) response));
		assertThat(delegate.chain, equalTo((FilterChain) filterChain));
		delegate = new MockFilter();
	}
