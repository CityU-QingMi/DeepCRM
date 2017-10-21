	private void assertFilterNotInvoked(String requestUri, String pattern) throws Exception {
		request.setRequestURI(request.getContextPath() + requestUri);
		filter = new PatternMappingFilterProxy(delegate, pattern);
		filter.doFilter(request, response, filterChain);

		assertThat(delegate.request, equalTo((ServletRequest) null));
		assertThat(delegate.response, equalTo((ServletResponse) null));
		assertThat(delegate.chain, equalTo((FilterChain) null));

		assertThat(filterChain.getRequest(), equalTo((ServletRequest) request));
		assertThat(filterChain.getResponse(), equalTo((ServletResponse) response));
		filterChain = new MockFilterChain();
	}
