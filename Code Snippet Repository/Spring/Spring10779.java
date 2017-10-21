	@Test
	public void getParameterValuesInvalidName() throws Exception {
		request.addParameter("name", "value1");
		request.addParameter("name", "value2");
		request.setContent("anotherName=anotherValue".getBytes("ISO-8859-1"));

		filter.doFilter(request, response, filterChain);
		String[] values = filterChain.getRequest().getParameterValues("noSuchParameter");

		assertNotSame("Request not wrapped", request, filterChain.getRequest());
		assertNull(values);
	}
