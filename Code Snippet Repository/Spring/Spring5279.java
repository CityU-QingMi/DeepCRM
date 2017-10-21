	@Test
	public void setDefaultProfiles() {
		environment.setDefaultProfiles();
		assertThat(environment.getDefaultProfiles().length, is(0));
		environment.setDefaultProfiles("pd1");
		assertThat(Arrays.asList(environment.getDefaultProfiles()), hasItem("pd1"));
		environment.setDefaultProfiles("pd2", "pd3");
		assertThat(Arrays.asList(environment.getDefaultProfiles()), not(hasItem("pd1")));
		assertThat(Arrays.asList(environment.getDefaultProfiles()), hasItems("pd2", "pd3"));
	}
