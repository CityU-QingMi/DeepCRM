	@Override
	public String[] extractCommands(Reader reader) {
		final SqlStatementLexer lexer = new SqlStatementLexer( reader );
		final SqlStatementParser parser = new SqlStatementParser( lexer );
		try {
			parser.script(); // Parse script.
			parser.throwExceptionIfErrorOccurred();
		}
		catch ( Exception e ) {
			throw new ImportScriptException( "Error during import script parsing.", e );
		}
		List<String> statementList = parser.getStatementList();
		return statementList.toArray( new String[statementList.size()] );
	}
