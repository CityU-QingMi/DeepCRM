	@Test
	public void withMixOfOptionsHavingValueAndOptionsHavingNoValue() {
		SimpleCommandLineArgsParser parser = new SimpleCommandLineArgsParser();
		CommandLineArgs args = parser.parse("--o1=v1", "--o2");
		assertThat(args.containsOption("o1"), is(true));
		assertThat(args.containsOption("o2"), is(true));
		assertThat(args.containsOption("o3"), is(false));
		assertThat(args.getOptionValues("o1").get(0), equalTo("v1"));
		assertThat(args.getOptionValues("o2"), equalTo(Collections.EMPTY_LIST));
		assertThat(args.getOptionValues("o3"), nullValue());
	}
