	@Test
	void create() {
		LauncherDiscoveryRequest discoveryRequest = createLauncherDiscoveryRequestForBothStandardEngineExampleClasses();

		TestPlan testPlan = LauncherFactory.create().discover(discoveryRequest);
		Set<TestIdentifier> roots = testPlan.getRoots();
		assertThat(roots).hasSize(2);

		// @formatter:off
		List<String> ids = roots.stream()
				.map(TestIdentifier::getUniqueId)
				.collect(toList());
		// @formatter:on

		assertThat(ids).containsOnly("[engine:junit-vintage]", "[engine:junit-jupiter]");
	}
