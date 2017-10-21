	ConsoleLauncherExecutionResult execute(String... args) {
		CommandLineOptions options = commandLineOptionsParser.parse(args);
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outStream, charset)))) {
			if (options.isDisplayHelp()) {
				commandLineOptionsParser.printHelp(out);
				return ConsoleLauncherExecutionResult.success();
			}
			return executeTests(options, out);
		}
		finally {
			outStream.flush();
			errStream.flush();
		}
	}
