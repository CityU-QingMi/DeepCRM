	@Test
	public void withCustomNonOptionArgsNameAndNoNonOptionArgsPresent() {
		CommandLinePropertySource<?> ps =
			new SimpleCommandLinePropertySource("--o1=v1", "noa1", "--o2", "noa2");
		ps.setNonOptionArgsPropertyName("NOA");

		assertThat(ps.containsProperty("nonOptionArgs"), is(false));
		assertThat(ps.containsProperty("NOA"), is(true));
		assertThat(ps.containsProperty("o1"), is(true));
		assertThat(ps.containsProperty("o2"), is(true));
		String nonOptionArgs = ps.getProperty("NOA");
		assertThat(nonOptionArgs, equalTo("noa1,noa2"));
	}
