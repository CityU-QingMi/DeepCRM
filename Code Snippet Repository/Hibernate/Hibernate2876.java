	public LocalTemporaryTableBulkIdStrategy() {
		this(
				new IdTableSupportStandardImpl() {
					@Override
					public String getCreateIdTableCommand() {
						return "create local temporary table";
					}
				},
				AfterUseAction.DROP,
				null
		);
	}
