	private void addMultiTableBulkIdStrategies(StrategySelectorImpl strategySelector) {
		strategySelector.registerStrategyImplementor(
				MultiTableBulkIdStrategy.class,
				PersistentTableBulkIdStrategy.SHORT_NAME,
				PersistentTableBulkIdStrategy.class
		);
		strategySelector.registerStrategyImplementor(
				MultiTableBulkIdStrategy.class,
				GlobalTemporaryTableBulkIdStrategy.SHORT_NAME,
				GlobalTemporaryTableBulkIdStrategy.class
		);
		strategySelector.registerStrategyImplementor(
				MultiTableBulkIdStrategy.class,
				LocalTemporaryTableBulkIdStrategy.SHORT_NAME,
				LocalTemporaryTableBulkIdStrategy.class
		);
	}
