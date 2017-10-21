	@Test
	public void loadIndexSeveralMatches() {
		CandidateComponentsIndex index = CandidateComponentsIndexLoader.loadIndex(
				CandidateComponentsTestClassLoader.index(getClass().getClassLoader(),
						new ClassPathResource("spring.components", getClass())));
		Set<String> components = index.getCandidateTypes("org.springframework", "foo");
		assertThat(components, containsInAnyOrder(
				"org.springframework.context.index.Sample1",
				"org.springframework.context.index.Sample2"));
	}
