	private NameSpaceTablesInformation processTableResults(ResultSet resultSet) throws SQLException {
		try {
			NameSpaceTablesInformation tables = new NameSpaceTablesInformation(identifierHelper());
			while ( resultSet.next() ) {
				final TableInformation tableInformation = extractTableInformation( resultSet );
				tables.addTableInformation( tableInformation );
			}

			return tables;
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException ignore) {
			}
		}
	}
