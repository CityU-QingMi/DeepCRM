	@Test
	public void withNonOptionArguments() {
		SimpleCommandLineArgsParser parser = new SimpleCommandLineArgsParser();
		CommandLineArgs args = parser.parse("--o1=v1", "noa1", "--o2=v2", "noa2");
		assertThat(args.getOptionValues("o1").get(0), equalTo("v1"));
		assertThat(args.getOptionValues("o2").get(0), equalTo("v2"));

		List<String> nonOptions = args.getNonOptionArgs();
		assertThat(nonOptions.get(0), equalTo("noa1"));
		assertThat(nonOptions.get(1), equalTo("noa2"));
		assertThat(nonOptions.size(), equalTo(2));
	}
