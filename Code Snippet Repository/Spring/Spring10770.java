	@Test
	public void filterWithParameter() throws IOException, ServletException {
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/hotels");
		request.addParameter("_method", "delete");
		MockHttpServletResponse response = new MockHttpServletResponse();

		FilterChain filterChain = new FilterChain() {

			@Override
			public void doFilter(ServletRequest filterRequest,
					ServletResponse filterResponse) throws IOException, ServletException {
				assertEquals("Invalid method", "DELETE",
						((HttpServletRequest) filterRequest).getMethod());
			}
		};
		filter.doFilter(request, response, filterChain);
	}
