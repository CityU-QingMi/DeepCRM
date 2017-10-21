	public static RequestMatcher header(final String name, final String... expectedValues) {
		return request -> {
			assertValueCount("header", name, request.getHeaders(), expectedValues.length);
			List<String> headerValues = request.getHeaders().get(name);
			Assert.state(headerValues != null, "No header values");
			for (int i = 0; i < expectedValues.length; i++) {
				assertEquals("Request header  [" + name + "]",
						expectedValues[i], headerValues.get(i));

			}
		};
	}
