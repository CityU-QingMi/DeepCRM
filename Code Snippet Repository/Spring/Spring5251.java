	@Test
	public void withNoArg() {
		OptionParser parser = new OptionParser();
		parser.accepts("o1");
		parser.accepts("o2");
		OptionSet options = parser.parse("--o1");

		PropertySource<?> ps = new JOptCommandLinePropertySource(options);
		assertThat(ps.containsProperty("o1"), is(true));
		assertThat(ps.containsProperty("o2"), is(false));
		assertThat((String)ps.getProperty("o1"), equalTo(""));
		assertThat(ps.getProperty("o2"), nullValue());
	}
