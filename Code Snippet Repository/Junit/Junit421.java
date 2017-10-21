	@Test
	void beforeAndAfterEachMethodsAreExecutedAroundInvocation() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(TestTemplateTestClassWithBeforeAndAfterEach.class, "testTemplateWithTwoInvocations")).build();

		executeTests(request);

		assertThat(TestTemplateTestClassWithBeforeAndAfterEach.lifecycleEvents).containsExactly(
			"beforeAll:TestTemplateInvocationTests$TestTemplateTestClassWithBeforeAndAfterEach", "beforeEach:[1]",
			"afterEach:[1]", "beforeEach:[2]", "afterEach:[2]",
			"afterAll:TestTemplateInvocationTests$TestTemplateTestClassWithBeforeAndAfterEach");
	}
