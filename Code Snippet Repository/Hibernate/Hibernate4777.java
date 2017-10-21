	@Override
	public String[] extractCommands(Reader reader) {
		BufferedReader bufferedReader = new BufferedReader( reader );
		List<String> statementList = new LinkedList<String>();
		try {
			for ( String sql = bufferedReader.readLine(); sql != null; sql = bufferedReader.readLine() ) {
				String trimmedSql = sql.trim();
				if ( StringHelper.isEmpty( trimmedSql ) || isComment( trimmedSql ) ) {
					continue;
				}
				if ( trimmedSql.endsWith( ";" ) ) {
					trimmedSql = trimmedSql.substring( 0, trimmedSql.length() - 1 );
				}
				statementList.add( trimmedSql );
			}
			return statementList.toArray( new String[statementList.size()] );
		}
		catch ( IOException e ) {
			throw new ImportScriptException( "Error during import script parsing.", e );
		}
	}
