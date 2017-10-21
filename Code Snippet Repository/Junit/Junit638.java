	@Override
	public void printHelp(Writer writer) {
		OptionParser optionParser = getAvailableOptions().getParser();
		optionParser.formatHelpWith(new OrderPreservingHelpFormatter());
		try {
			optionParser.printHelpOn(writer);
		}
		catch (IOException e) {
			throw new JUnitException("Error printing help", e);
		}
	}
