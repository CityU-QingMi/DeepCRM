	@Override
	public MultiTableBulkIdStrategy getDefaultMultiTableBulkIdStrategy() {
		return new LocalTemporaryTableBulkIdStrategy(
				new IdTableSupportStandardImpl() {
					@Override
					public String getCreateIdTableCommand() {
						return "create temporary table if not exists";
					}

					@Override
					public String getDropIdTableCommand() {
						return "drop temporary table";
					}
				},
				AfterUseAction.DROP,
				TempTableDdlTransactionHandling.NONE
		);
	}
