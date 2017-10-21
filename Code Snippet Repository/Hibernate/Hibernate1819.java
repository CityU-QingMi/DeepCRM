	@Override
	public MultiTableBulkIdStrategy getDefaultMultiTableBulkIdStrategy() {
		return new LocalTemporaryTableBulkIdStrategy(
				new IdTableSupportStandardImpl() {
					@Override
					public String getCreateIdTableCommand() {
						return "create temporary table";
					}

					@Override
					public String getCreateIdTableStatementOptions() {
						return "on commit drop";
					}
				},
				AfterUseAction.CLEAN,
				null
		);
	}
