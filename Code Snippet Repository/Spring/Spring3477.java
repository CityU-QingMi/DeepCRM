	@Test
	public void mergeCandidateStereotypes() {
		CandidateComponentsIndex index = new CandidateComponentsIndex(Arrays.asList(
				createProperties("com.example.Foo", "service"),
				createProperties("com.example.Foo", "entity")));
		assertThat(index.getCandidateTypes("com.example", "service"),
				contains("com.example.Foo"));
		assertThat(index.getCandidateTypes("com.example", "entity"),
				contains("com.example.Foo"));
	}
