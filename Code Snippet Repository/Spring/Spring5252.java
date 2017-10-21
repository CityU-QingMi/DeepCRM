	@Test
	public void withRequiredArg_andMultipleArgsPresent_usingDelimiter() {
		OptionParser parser = new OptionParser();
		parser.accepts("foo").withRequiredArg().withValuesSeparatedBy(',');
		OptionSet options = parser.parse("--foo=bar,baz,biz");

		CommandLinePropertySource<?> ps = new JOptCommandLinePropertySource(options);
		assertEquals(Arrays.asList("bar","baz","biz"), ps.getOptionValues("foo"));
		assertThat(ps.getProperty("foo"), equalTo("bar,baz,biz"));
	}
