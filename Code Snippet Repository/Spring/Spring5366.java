	private void assertBogusActiveTestGroupBehavior(String testGroups) {
		// Should result in something similar to the following:
		//
		// java.lang.IllegalStateException: Failed to parse 'testGroups' system property:
		// Unable to find test group 'bogus' when parsing testGroups value: 'all-bogus'.
		// Available groups include: [LONG_RUNNING,PERFORMANCE,JMXMP,CI]

		setTestGroups(testGroups);
		try {
			Assume.group(JMXMP);
			fail("assumption should have failed");
		}
		catch (IllegalStateException ex) {
			assertThat(ex.getMessage(),
				startsWith("Failed to parse '" + TEST_GROUPS_SYSTEM_PROPERTY + "' system property: "));

			assertThat(ex.getCause(), instanceOf(IllegalArgumentException.class));
			assertThat(ex.getCause().getMessage(),
				equalTo("Unable to find test group 'bogus' when parsing testGroups value: '" + testGroups
						+ "'. Available groups include: [LONG_RUNNING,PERFORMANCE,JMXMP,CI]"));
		}
	}
