	private static void bindCustomSql(
			MappingDocument sourceDocument,
			SecondaryTableSource secondaryTableSource,
			Join secondaryTable) {
		if ( secondaryTableSource.getCustomSqlInsert() != null ) {
			secondaryTable.setCustomSQLInsert(
					secondaryTableSource.getCustomSqlInsert().getSql(),
					secondaryTableSource.getCustomSqlInsert().isCallable(),
					secondaryTableSource.getCustomSqlInsert().getCheckStyle()
			);
		}

		if ( secondaryTableSource.getCustomSqlUpdate() != null ) {
			secondaryTable.setCustomSQLUpdate(
					secondaryTableSource.getCustomSqlUpdate().getSql(),
					secondaryTableSource.getCustomSqlUpdate().isCallable(),
					secondaryTableSource.getCustomSqlUpdate().getCheckStyle()
			);
		}

		if ( secondaryTableSource.getCustomSqlDelete() != null ) {
			secondaryTable.setCustomSQLDelete(
					secondaryTableSource.getCustomSqlDelete().getSql(),
					secondaryTableSource.getCustomSqlDelete().isCallable(),
					secondaryTableSource.getCustomSqlDelete().getCheckStyle()
			);
		}
	}
