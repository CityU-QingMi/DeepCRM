	private void assertResult(ConditionEvaluationResult result, boolean disabled, Matcher<String> matcher) {
		assertNotNull(result);

		if (disabled) {
			assertTrue(result.isDisabled());
		}
		else {
			assertFalse(result.isDisabled());
		}

		Optional<String> reason = result.getReason();
		assertTrue(reason.isPresent());
		assertThat(reason.get(), matcher);
	}
