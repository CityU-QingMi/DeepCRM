	private static void assertRequestAttributes(ServletRequest request, boolean withinMockMvc) {
		if (withinMockMvc) {
			assertThat(request.getAttribute(FROM_TCF_MOCK), is(nullValue()));
			assertThat(request.getAttribute(FROM_MVC_TEST_DEFAULT), is(FROM_MVC_TEST_DEFAULT));
			assertThat(request.getAttribute(FROM_MVC_TEST_MOCK), is(FROM_MVC_TEST_MOCK));
			assertThat(request.getAttribute(FROM_REQUEST_FILTER), is(FROM_REQUEST_FILTER));
			assertThat(request.getAttribute(FROM_REQUEST_ATTRIBUTES_FILTER), is(FROM_REQUEST_ATTRIBUTES_FILTER));
		}
		else {
			assertThat(request.getAttribute(FROM_TCF_MOCK), is(FROM_TCF_MOCK));
			assertThat(request.getAttribute(FROM_MVC_TEST_DEFAULT), is(nullValue()));
			assertThat(request.getAttribute(FROM_MVC_TEST_MOCK), is(nullValue()));
			assertThat(request.getAttribute(FROM_REQUEST_FILTER), is(nullValue()));
			assertThat(request.getAttribute(FROM_REQUEST_ATTRIBUTES_FILTER), is(nullValue()));
		}
	}
