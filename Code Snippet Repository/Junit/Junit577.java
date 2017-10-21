	@Test
	void executesLifecycleMethods() {
		// reset static collections
		LifecycleTestCase.lifecycleEvents.clear();
		LifecycleTestCase.testMethods.clear();

		List<ExecutionEvent> executionEvents = execute(selectClass(LifecycleTestCase.class));
		assertThat(executionEvents) //
				.haveExactly(1, event(test("test1"), displayName("[1] foo"), finishedWithFailure(message("foo")))) //
				.haveExactly(1, event(test("test1"), displayName("[2] bar"), finishedWithFailure(message("bar"))));

		List<String> testMethods = new ArrayList<>(LifecycleTestCase.testMethods);

		// @formatter:off
		assertThat(LifecycleTestCase.lifecycleEvents).containsExactly(
			"beforeAll:ParameterizedTestIntegrationTests$LifecycleTestCase", //
				"providerMethod",
					"constructor:ParameterizedTestIntegrationTests$LifecycleTestCase",
					"beforeEach:[1] foo",
						testMethods.get(0) + ":[1] foo",
					"afterEach:[1] foo",
					"constructor:ParameterizedTestIntegrationTests$LifecycleTestCase",
					"beforeEach:[2] bar",
						testMethods.get(0) + ":[2] bar",
					"afterEach:[2] bar",
				"providerMethod",
					"constructor:ParameterizedTestIntegrationTests$LifecycleTestCase",
					"beforeEach:[1] foo",
						testMethods.get(1) + ":[1] foo",
					"afterEach:[1] foo",
					"constructor:ParameterizedTestIntegrationTests$LifecycleTestCase",
					"beforeEach:[2] bar",
						testMethods.get(1) + ":[2] bar",
					"afterEach:[2] bar",
			"afterAll:ParameterizedTestIntegrationTests$LifecycleTestCase");
		// @formatter:on
	}
