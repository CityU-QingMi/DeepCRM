	@Test
	public void getParameterValues() throws Exception {
		request.setQueryString("name=value1&name=value2");
		request.addParameter("name", "value1");
		request.addParameter("name", "value2");
		request.setContent("name=value3&name=value4".getBytes("ISO-8859-1"));

		filter.doFilter(request, response, filterChain);
		String[] values = filterChain.getRequest().getParameterValues("name");

		assertNotSame("Request not wrapped", request, filterChain.getRequest());
		assertArrayEquals(new String[]{"value1", "value2", "value3", "value4"}, values);
	}
