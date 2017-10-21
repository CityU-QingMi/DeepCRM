	@Test
	public void withCustomNonOptionArgsNameAndNoNonOptionArgsPresent() {
		OptionParser parser = new OptionParser();
		parser.accepts("o1").withRequiredArg();
		parser.accepts("o2");
		OptionSet optionSet = parser.parse("--o1=v1", "noa1", "--o2", "noa2");
		CommandLinePropertySource<?> ps = new JOptCommandLinePropertySource(optionSet);
		ps.setNonOptionArgsPropertyName("NOA");

		assertThat(ps.containsProperty("nonOptionArgs"), is(false));
		assertThat(ps.containsProperty("NOA"), is(true));
		assertThat(ps.containsProperty("o1"), is(true));
		assertThat(ps.containsProperty("o2"), is(true));
		String nonOptionArgs = ps.getProperty("NOA");
		assertThat(nonOptionArgs, equalTo("noa1,noa2"));
	}
