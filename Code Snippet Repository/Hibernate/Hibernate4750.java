	public static void main(String[] args) {
		try {
			final CommandLineArgs commandLineArgs = CommandLineArgs.parseCommandLineArgs( args );
			execute( commandLineArgs );
		}
		catch (Exception e) {
			LOG.unableToCreateSchema( e );
			e.printStackTrace();
		}
	}
