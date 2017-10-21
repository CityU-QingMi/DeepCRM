	private ConsoleLauncherExecutionResult executeTests(CommandLineOptions options, PrintWriter out) {
		try {
			TestExecutionSummary testExecutionSummary = new ConsoleTestExecutor(options).execute(out);
			return ConsoleLauncherExecutionResult.forSummary(testExecutionSummary);
		}
		catch (Exception exception) {
			exception.printStackTrace(errStream);
			errStream.println();
			commandLineOptionsParser.printHelp(out);
		}
		return ConsoleLauncherExecutionResult.failed();
	}
