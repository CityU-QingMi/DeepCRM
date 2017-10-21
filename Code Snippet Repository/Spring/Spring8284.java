	@SafeVarargs
	public static RequestMatcher header(final String name, final Matcher<? super String>... matchers) {
		return request -> {
			assertValueCount("header", name, request.getHeaders(), matchers.length);
			List<String> headerValues = request.getHeaders().get(name);
			Assert.state(headerValues != null, "No header values");
			for (int i = 0; i < matchers.length; i++) {
				assertThat("Request header[" + name + "]", headerValues.get(i), matchers[i]);

			}
		};
	}
