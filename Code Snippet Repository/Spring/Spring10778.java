	@Test
	public void getParameterValuesFromFormContent() throws Exception {
		request.addParameter("name", "value1");
		request.addParameter("name", "value2");
		request.setContent("anotherName=anotherValue".getBytes("ISO-8859-1"));

		filter.doFilter(request, response, filterChain);
		String[] values = filterChain.getRequest().getParameterValues("anotherName");

		assertNotSame("Request not wrapped", request, filterChain.getRequest());
		assertArrayEquals(new String[]{"anotherValue"}, values);
	}
