	@Test
	public void getParameterMap() throws Exception {
		request.setQueryString("name=value1&name=value2");
		request.addParameter("name", "value1");
		request.addParameter("name", "value2");
		request.setContent("name=value3&name4=value4".getBytes("ISO-8859-1"));

		filter.doFilter(request, response, filterChain);
		Map<String, String[]> parameters = filterChain.getRequest().getParameterMap();

		assertNotSame("Request not wrapped", request, filterChain.getRequest());
		assertEquals(2, parameters.size());
		assertArrayEquals(new String[] {"value1", "value2", "value3"}, parameters.get("name"));
		assertArrayEquals(new String[] {"value4"}, parameters.get("name4"));
	}
