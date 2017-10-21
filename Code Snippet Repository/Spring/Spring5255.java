	@Test
	public void withDefaultNonOptionArgsNameAndNonOptionArgsPresent() {
		OptionParser parser = new OptionParser();
		parser.accepts("o1").withRequiredArg();
		parser.accepts("o2");
		OptionSet optionSet = parser.parse("--o1=v1", "noa1", "--o2", "noa2");
		PropertySource<?> ps = new JOptCommandLinePropertySource(optionSet);

		assertThat(ps.containsProperty("nonOptionArgs"), is(true));
		assertThat(ps.containsProperty("o1"), is(true));
		assertThat(ps.containsProperty("o2"), is(true));

		String nonOptionArgs = (String)ps.getProperty("nonOptionArgs");
		assertThat(nonOptionArgs, equalTo("noa1,noa2"));
	}
