	private String generateDelete(
			String tableName,
			String[] columnNames,
			String idSubselect,
			String comment) {
		final Delete delete = new Delete().setTableName( tableName ).setWhere(
				"(" + String.join( ", ", (CharSequence[]) columnNames ) + ") in ("
						+ idSubselect + ")" );
		if ( factory().getSessionFactoryOptions().isCommentsEnabled() ) {
			delete.setComment( comment );
		}
		return delete.toStatementString();
	}
