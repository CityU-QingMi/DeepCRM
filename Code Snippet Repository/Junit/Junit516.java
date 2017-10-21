	@Test
	void beforeEachMethodThrowsAnException() {
		LauncherDiscoveryRequest request = request().selectors(
			selectClass(ExceptionInBeforeEachMethodTestCase.class)).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(0, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestSkippedCount(), "# tests skipped");
		assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed");

		// Since the JVM does not guarantee the order in which methods are
		// returned via reflection (and since JUnit Jupiter does not yet
		// support ordering of @BeforeEach methods), we have to figure out
		// which @BeforeEach method got executed first in order to determine
		// the expected call sequence.

		// @formatter:off
		List<String> list1 = asList(
			"fooBeforeEachCallback",
				"beforeEachMethod1", // throws an exception.
				// "beforeEachMethod2" should not get invoked
					// test should not get invoked.
				"afterEachMethod",
			"fooAfterEachCallback"
		);
		List<String> list2 = asList(
			"fooBeforeEachCallback",
				"beforeEachMethod2",
				"beforeEachMethod1", // throws an exception.
					// test should not get invoked.
				"afterEachMethod",
			"fooAfterEachCallback"
		);
		// @formatter:on

		List<String> expected = beforeEachMethodCallSequence.get(0).equals("beforeEachMethod1") ? list1 : list2;

		assertEquals(expected, callSequence, "wrong call sequence");

		assertThat(actualExceptionInAfterEachCallback).containsInstanceOf(EnigmaException.class);
	}
