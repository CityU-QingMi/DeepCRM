	@Test
	void launcherWillNotExecuteEnginesIfNotIncludedByAnEngineFilter() {
		DemoHierarchicalTestEngine firstEngine = new DemoHierarchicalTestEngine("first");
		TestDescriptor test1 = firstEngine.addTest("test1", noOp);
		DemoHierarchicalTestEngine secondEngine = new DemoHierarchicalTestEngine("second");
		TestDescriptor test2 = secondEngine.addTest("test2", noOp);

		DefaultLauncher launcher = createLauncher(firstEngine, secondEngine);

		// @formatter:off
		TestPlan testPlan = launcher.discover(
			request()
				.selectors(selectUniqueId(test1.getUniqueId()), selectUniqueId(test2.getUniqueId()))
				.filters(includeEngines("first"))
				.build());
		// @formatter:on

		assertThat(testPlan.getRoots()).hasSize(1);
		TestIdentifier rootIdentifier = testPlan.getRoots().iterator().next();
		assertThat(testPlan.getChildren(rootIdentifier.getUniqueId())).hasSize(1);
		assertThat(testPlan.getChildren(UniqueId.forEngine("first").toString())).hasSize(1);
	}
