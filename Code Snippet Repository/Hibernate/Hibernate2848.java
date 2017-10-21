	public GlobalTemporaryTableBulkIdStrategy(AfterUseAction afterUseAction) {
		this(
				new IdTableSupportStandardImpl() {
					@Override
					public String getCreateIdTableCommand() {
						return "create global temporary table";
					}

					@Override
					public String getDropIdTableCommand() {
						return super.getDropIdTableCommand();
					}
				},
				afterUseAction
		);
	}
