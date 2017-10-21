	@Test
	public void resolveStringArray() throws Exception {
		String[] expected = new String[] {"foo", "bar"};
		request.addParameter("name", expected);

		MethodParameter param = this.testMethod.annotPresent(RequestParam.class).arg(String[].class);
		Object result = resolver.resolveArgument(param, null, webRequest, null);
		assertTrue(result instanceof String[]);
		assertArrayEquals("Invalid result", expected, (String[]) result);
	}
