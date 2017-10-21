	@Test
	public void filterWithNoParameter() throws IOException, ServletException {
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/hotels");
		MockHttpServletResponse response = new MockHttpServletResponse();

		FilterChain filterChain = new FilterChain() {

			@Override
			public void doFilter(ServletRequest filterRequest,
					ServletResponse filterResponse) throws IOException, ServletException {
				assertEquals("Invalid method", "POST",
						((HttpServletRequest) filterRequest).getMethod());
			}
		};
		filter.doFilter(request, response, filterChain);
	}
