	@Test
	public void resolveMapArgument() throws Exception {
		String name = "foo";
		String value = "bar";
		request.addParameter(name, value);
		Map<String, String> expected = Collections.singletonMap(name, value);

		MethodParameter param = this.testMethod.annot(requestParam().noName()).arg(Map.class);
		Object result = resolver.resolveArgument(param, null, webRequest, null);

		assertTrue(result instanceof Map);
		assertEquals("Invalid result", expected, result);
	}
