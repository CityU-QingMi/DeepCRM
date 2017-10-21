	@SafeVarargs
	public static RequestMatcher queryParam(final String name, final Matcher<? super String>... matchers) {
		return request -> {
			MultiValueMap<String, String> params = getQueryParams(request);
			assertValueCount("query param", name, params, matchers.length);
			for (int i = 0 ; i < matchers.length; i++) {
				assertThat("Query param", params.get(name).get(i), matchers[i]);
			}
		};
	}
