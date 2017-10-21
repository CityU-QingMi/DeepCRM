	@Test
	public void withDefaultNonOptionArgsNameAndNoNonOptionArgsPresent() {
		OptionParser parser = new OptionParser();
		parser.acceptsAll(Arrays.asList("o1","option1")).withRequiredArg();
		parser.accepts("o2");
		OptionSet optionSet = parser.parse("--o1=v1", "--o2");
		EnumerablePropertySource<?> ps = new JOptCommandLinePropertySource(optionSet);

		assertThat(ps.containsProperty("nonOptionArgs"), is(false));
		assertThat(ps.containsProperty("o1"), is(true));
		assertThat(ps.containsProperty("o2"), is(true));

		assertThat(ps.containsProperty("nonOptionArgs"), is(false));
		assertThat(ps.getProperty("nonOptionArgs"), nullValue());
		assertThat(ps.getPropertyNames().length, is(2));
	}
