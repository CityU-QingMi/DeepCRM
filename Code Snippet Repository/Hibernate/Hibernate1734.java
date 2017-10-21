	@Override
	public MultiTableBulkIdStrategy getDefaultMultiTableBulkIdStrategy() {
		return new LocalTemporaryTableBulkIdStrategy(new IdTableSupportStandardImpl() {
			@Override
			public String generateIdTableName(String baseName) {
				return "session." + super.generateIdTableName( baseName );
			}

			@Override
			public String getCreateIdTableCommand() {
				return "declare global temporary table";
			}

			@Override
			public String getCreateIdTableStatementOptions() {
				return "not logged";
			}
		}, AfterUseAction.CLEAN, null);
	}
