	@Override
	public MultiTableBulkIdStrategy getDefaultMultiTableBulkIdStrategy() {
		return new LocalTemporaryTableBulkIdStrategy(
				new IdTableSupportStandardImpl() {
					@Override
					public String getCreateIdTableCommand() {
						return "create temp table";
					}

					@Override
					public String getCreateIdTableStatementOptions() {
						return "with no log";
					}
				},
				AfterUseAction.CLEAN,
				null
		);
	}
