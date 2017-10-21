	@Test
	void withAdditionalListener() {
		TestEngineSpy engine = new TestEngineSpy();
		SummaryGeneratingListener listener = new SummaryGeneratingListener();

		DefaultLauncher launcher = createLauncher(engine);
		launcher.execute(request().build(), listener);

		assertThat(listener.getSummary()).isNotNull();
		assertThat(listener.getSummary().getContainersFoundCount()).isEqualTo(1);
		assertThat(listener.getSummary().getTestsFoundCount()).isEqualTo(1);
	}
