	@Test
	void displayHelp() {
		CommandLineOptions options = new CommandLineOptions();
		options.setDisplayHelp(true);

		CommandLineOptionsParser commandLineOptionsParser = mock(CommandLineOptionsParser.class);
		when(commandLineOptionsParser.parse(any())).thenReturn(options);

		ConsoleLauncher consoleLauncher = new ConsoleLauncher(commandLineOptionsParser, printSink, printSink);
		int exitCode = consoleLauncher.execute("--help").getExitCode();

		assertEquals(0, exitCode);
		verify(commandLineOptionsParser).parse("--help");
	}
