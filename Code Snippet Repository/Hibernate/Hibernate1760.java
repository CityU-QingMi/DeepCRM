	@Override
	public MultiTableBulkIdStrategy getDefaultMultiTableBulkIdStrategy() {
		return new LocalTemporaryTableBulkIdStrategy(
				new IdTableSupportStandardImpl() {
					@Override
					public String getCreateIdTableCommand() {
						return "create cached local temporary table if not exists";
					}

					@Override
					public String getCreateIdTableStatementOptions() {
						// actually 2 different options are specified here:
						//		1) [on commit drop] - says to drop the table on transaction commit
						//		2) [transactional] - says to not perform an implicit commit of any current transaction
						return "on commit drop transactional";					}
				},
				AfterUseAction.CLEAN,
				TempTableDdlTransactionHandling.NONE
		);
	}
