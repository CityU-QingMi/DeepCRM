	@Test
	public void withOptionalArg_andArgIsMissing() {
		OptionParser parser = new OptionParser();
		parser.accepts("foo").withOptionalArg();
		OptionSet options = parser.parse("--foo");

		PropertySource<?> ps = new JOptCommandLinePropertySource(options);
		assertThat(ps.containsProperty("foo"), is(true));
		assertThat((String)ps.getProperty("foo"), equalTo(""));
	}
