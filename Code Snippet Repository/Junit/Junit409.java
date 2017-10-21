	@Test
	void lifecycleCallbacksAreExecutedForInvocation() {
		LauncherDiscoveryRequest request = request().selectors(
			selectClass(TestTemplateTestClassWithDynamicLifecycleCallbacks.class)).build();

		executeTests(request);

		// @formatter:off
		assertThat(TestTemplateTestClassWithDynamicLifecycleCallbacks.lifecycleEvents).containsExactly(
			"beforeEach",
				"beforeTestExecution",
					"testTemplate:foo",
					"handleTestExecutionException",
				"afterTestExecution",
			"afterEach",
			"beforeEach",
				"beforeTestExecution",
					"testTemplate:bar",
				"afterTestExecution",
			"afterEach");
		// @formatter:on
	}
