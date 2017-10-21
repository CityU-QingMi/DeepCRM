	@Override
	public void accept(String command) {
		try {
			writer().write( command );
			writer().write( System.lineSeparator() );
			writer().flush();
		}
		catch (IOException e) {
			throw new CommandAcceptanceException( "Could not write to target script file", e );
		}
	}
