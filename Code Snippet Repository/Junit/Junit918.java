	@Test
	void executeWithSupportedCommandLineOption() {
		CommandLineOptionsParser commandLineOptionsParser = mock(CommandLineOptionsParser.class);
		when(commandLineOptionsParser.parse(any())).thenReturn(new CommandLineOptions());

		ConsoleLauncher consoleLauncher = new ConsoleLauncher(commandLineOptionsParser, printSink, printSink);
		int exitCode = consoleLauncher.execute("--scan-classpath").getExitCode();

		assertEquals(-1, exitCode);
		verify(commandLineOptionsParser).parse("--scan-classpath");
	}
