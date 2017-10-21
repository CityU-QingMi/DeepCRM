	@Test
	public void addActiveProfile() {
		assertThat(environment.getActiveProfiles().length, is(0));
		environment.setActiveProfiles("local", "embedded");
		assertThat(Arrays.asList(environment.getActiveProfiles()), hasItems("local", "embedded"));
		assertThat(environment.getActiveProfiles().length, is(2));
		environment.addActiveProfile("p1");
		assertThat(Arrays.asList(environment.getActiveProfiles()), hasItems("p1"));
		assertThat(environment.getActiveProfiles().length, is(3));
		environment.addActiveProfile("p2");
		environment.addActiveProfile("p3");
		assertThat(Arrays.asList(environment.getActiveProfiles()), hasItems("p2", "p3"));
		assertThat(environment.getActiveProfiles().length, is(5));
	}
