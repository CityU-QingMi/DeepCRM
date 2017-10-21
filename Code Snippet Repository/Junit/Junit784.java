	@Test
	void resolvesCategoriesIntoTags() {
		Class<?> testClass = PlainJUnit4TestCaseWithFiveTestMethods.class;
		LauncherDiscoveryRequest discoveryRequest = discoveryRequestForClass(testClass);

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertThat(runnerDescriptor.getTags()).containsOnly(TestTag.create(Plain.class.getName()));

		TestDescriptor failingTest = findChildByDisplayName(runnerDescriptor, "failingTest");
		assertThat(failingTest.getTags()).containsOnly(//
			TestTag.create(Plain.class.getName()), //
			TestTag.create(Failing.class.getName()));

		TestDescriptor ignoredWithoutReason = findChildByDisplayName(runnerDescriptor, "ignoredTest1_withoutReason");
		assertThat(ignoredWithoutReason.getTags()).containsOnly(//
			TestTag.create(Plain.class.getName()), //
			TestTag.create(Skipped.class.getName()));

		TestDescriptor ignoredWithReason = findChildByDisplayName(runnerDescriptor, "ignoredTest2_withReason");
		assertThat(ignoredWithReason.getTags()).containsOnly(//
			TestTag.create(Plain.class.getName()), //
			TestTag.create(Skipped.class.getName()), //
			TestTag.create(SkippedWithReason.class.getName()));
	}
