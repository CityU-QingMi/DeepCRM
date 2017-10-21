	@Test
	public void withDefaultNonOptionArgsNameAndNonOptionArgsPresent() {
		CommandLinePropertySource<?> ps =
			new SimpleCommandLinePropertySource("--o1=v1", "noa1", "--o2", "noa2");

		assertThat(ps.containsProperty("nonOptionArgs"), is(true));
		assertThat(ps.containsProperty("o1"), is(true));
		assertThat(ps.containsProperty("o2"), is(true));

		String nonOptionArgs = ps.getProperty("nonOptionArgs");
		assertThat(nonOptionArgs, equalTo("noa1,noa2"));
	}
