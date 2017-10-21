	@Test
	public void getParameterFromQueryString() throws Exception {
		request.addParameter("name", "value1");
		request.setContent("name=value2".getBytes("ISO-8859-1"));
		filter.doFilter(request, response, filterChain);

		assertNotSame("Request not wrapped", request, filterChain.getRequest());
		assertEquals("Query string parameters should be listed ahead of form parameters",
				"value1", filterChain.getRequest().getParameter("name"));
	}
