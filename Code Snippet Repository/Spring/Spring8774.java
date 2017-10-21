	private void assertIncorrectResponseHeader(ResultMatcher matcher, String unexpected) throws Exception {
		try {
			this.mockMvc.perform(get("/persons/1")
					.header(IF_MODIFIED_SINCE, minuteAgo))
					.andExpect(matcher);

			fail(ERROR_MESSAGE);
		}
		catch (AssertionError err) {
			if (ERROR_MESSAGE.equals(err.getMessage())) {
				throw err;
			}
			// SPR-10659: ensure header name is in the message
			// Unfortunately, we can't control formatting from JUnit or Hamcrest.
			assertMessageContains(err, "Response header '" + LAST_MODIFIED + "'");
			assertMessageContains(err, unexpected);
			assertMessageContains(err, now);
		}
	}
