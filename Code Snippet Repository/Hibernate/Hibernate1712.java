	@Override
	public MultiTableBulkIdStrategy getDefaultMultiTableBulkIdStrategy() {
		return new LocalTemporaryTableBulkIdStrategy(
				new IdTableSupportStandardImpl() {
					@Override
					public String generateIdTableName(String baseName) {
						return "#" + baseName;
					}
				},
				// sql-server, at least needed this dropped after use; strange!
				AfterUseAction.DROP,
				TempTableDdlTransactionHandling.NONE
		);
	}
