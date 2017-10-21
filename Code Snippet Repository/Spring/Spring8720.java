	@Test
	public void testHttpStatusCodeResultMatchers() throws Exception {

		List<AssertionError> failures = new ArrayList<>();

		for (HttpStatus status : HttpStatus.values()) {
			MockHttpServletResponse response = new MockHttpServletResponse();
			response.setStatus(status.value());
			MvcResult mvcResult = new StubMvcResult(request, null, null, null, null, null, response);
			try {
				Method method = getMethodForHttpStatus(status);
				ResultMatcher matcher = (ResultMatcher) ReflectionUtils.invokeMethod(method, this.matchers);
				try {
					matcher.match(mvcResult);
				}
				catch (AssertionError error) {
					failures.add(error);
				}
			}
			catch (Exception ex) {
				throw new Exception("Failed to obtain ResultMatcher for status " + status, ex);
			}
		}

		if (!failures.isEmpty()) {
			fail("Failed status codes: " + failures);
		}
	}
