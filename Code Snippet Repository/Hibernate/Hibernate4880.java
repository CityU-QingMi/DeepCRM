	@Override
	public List<String> read(ImportSqlCommandExtractor commandExtractor) {
		final String[] commands = commandExtractor.extractCommands( reader() );
		if ( commands == null ) {
			return Collections.emptyList();
		}
		else {
			return Arrays.asList( commands );
		}
	}
