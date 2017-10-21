	protected Delete generateDelete(
			String tableName,
			String[] columnNames,
			String idSubselect,
			String comment) {
		final Delete delete = new Delete()
				.setTableName( tableName )
				.setWhere(idSubselect);
		if ( factory().getSessionFactoryOptions().isCommentsEnabled() ) {
			delete.setComment( comment );
		}
		return delete;
	}
