	@Override
	public CommandLineOptions parse(String... arguments) {
		AvailableOptions availableOptions = getAvailableOptions();
		OptionParser parser = availableOptions.getParser();
		try {
			OptionSet detectedOptions = parser.parse(arguments);
			return availableOptions.toCommandLineOptions(detectedOptions);
		}
		catch (OptionException e) {
			throw new JUnitException("Error parsing command-line arguments", e);
		}
	}
