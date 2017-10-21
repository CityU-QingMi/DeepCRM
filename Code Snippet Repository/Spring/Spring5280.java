	@Test
	public void acceptsProfiles_activeProfileSetProgrammatically() {
		assertThat(environment.acceptsProfiles("p1", "p2"), is(false));
		environment.setActiveProfiles("p1");
		assertThat(environment.acceptsProfiles("p1", "p2"), is(true));
		environment.setActiveProfiles("p2");
		assertThat(environment.acceptsProfiles("p1", "p2"), is(true));
		environment.setActiveProfiles("p1", "p2");
		assertThat(environment.acceptsProfiles("p1", "p2"), is(true));
	}
