	@Test
	public void resolveMultiValueMapArgument() throws Exception {
		String name = "foo";
		String value1 = "bar";
		String value2 = "baz";
		request.addParameter(name, value1, value2);

		MultiValueMap<String, String> expected = new LinkedMultiValueMap<>(1);
		expected.add(name, value1);
		expected.add(name, value2);

		MethodParameter param = this.testMethod.annotPresent(RequestParam.class).arg(MultiValueMap.class);
		Object result = resolver.resolveArgument(param, null, webRequest, null);

		assertTrue(result instanceof MultiValueMap);
		assertEquals("Invalid result", expected, result);
	}
