	@Test
	public void getParameterNames() throws Exception {
		request.addParameter("name1", "value1");
		request.addParameter("name2", "value2");
		request.setContent("name1=value1&name3=value3&name4=value4".getBytes("ISO-8859-1"));

		filter.doFilter(request, response, filterChain);
		List<String> names = Collections.list(filterChain.getRequest().getParameterNames());

		assertNotSame("Request not wrapped", request, filterChain.getRequest());
		assertEquals(Arrays.asList("name1", "name2", "name3", "name4"), names);
	}
