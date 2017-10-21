	@Override
	public List<String> collectSqlStrings() {
		ArrayList<String> list = new ArrayList<String>();
		if ( isManipulationStatement() ) {
			String[] sqlStatements = statementExecutor.getSqlStatements();
			Collections.addAll( list, sqlStatements );
		}
		else {
			list.add( sql );
		}
		return list;
	}
