	@Test
	void executeWithUnknownCommandLineOption() {
		CommandLineOptionsParser commandLineOptionsParser = mock(CommandLineOptionsParser.class);
		when(commandLineOptionsParser.parse(any())).thenReturn(new CommandLineOptions());

		ConsoleLauncher consoleLauncher = new ConsoleLauncher(commandLineOptionsParser, printSink, printSink);
		int exitCode = consoleLauncher.execute("--all").getExitCode();

		assertEquals(-1, exitCode);
		verify(commandLineOptionsParser).parse("--all");
	}
