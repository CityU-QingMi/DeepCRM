	@Test
	public void resolveString() throws Exception {
		String expected = "foo";
		request.addParameter("name", expected);

		MethodParameter param = this.testMethod.annot(requestParam().notRequired("bar")).arg(String.class);
		Object result = resolver.resolveArgument(param, null, webRequest, null);
		assertTrue(result instanceof String);
		assertEquals("Invalid result", expected, result);
	}
