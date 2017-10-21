	@Override
	public MultiTableBulkIdStrategy getDefaultMultiTableBulkIdStrategy() {
		return new LocalTemporaryTableBulkIdStrategy(
				new IdTableSupportStandardImpl() {
					@Override
					public String generateIdTableName(String baseName) {
						return "temp." + super.generateIdTableName( baseName );
					}

					@Override
					public String getCreateIdTableStatementOptions() {
						return "ignore rollback";
					}
				},
				AfterUseAction.DROP,
				null
		);
	}
