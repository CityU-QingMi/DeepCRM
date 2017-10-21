	@Override
	public MultiTableBulkIdStrategy getDefaultMultiTableBulkIdStrategy() {
		return new LocalTemporaryTableBulkIdStrategy(
				new IdTableSupportStandardImpl() {
					@Override
					public String getCreateIdTableCommand() {
						return "create temporary  table";
					}

					@Override
					public String getDropIdTableCommand() {
						return "drop table";
					}
				},
				AfterUseAction.DROP,
				null
		);
	}
