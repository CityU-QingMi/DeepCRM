	@Test
	void beforeAndAfterMethodsInTestClassHierarchy() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(TestCase3.class);

		// @formatter:off
		assertAll(
			() -> assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started"),
			() -> assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded"),
			() -> assertEquals(0, eventRecorder.getTestSkippedCount(), "# tests skipped"),
			() -> assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted"),
			() -> assertEquals(0, eventRecorder.getTestFailedCount(), "# tests failed")
		);
		// @formatter:on

		// @formatter:off
		assertEquals(asList(
			"beforeAll1",
				"beforeAll2",
					"beforeAll3",
						"beforeEach1",
							"beforeEach2",
								"beforeEach3",
									"test3",
								"afterEach3",
							"afterEach2",
						"afterEach1",
					"afterAll3",
				"afterAll2",
			"afterAll1"
		), callSequence, "wrong call sequence");
		// @formatter:on
	}
