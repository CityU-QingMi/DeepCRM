	private void addEntityCopyObserverStrategies(StrategySelectorImpl strategySelector) {
		strategySelector.registerStrategyImplementor(
				EntityCopyObserver.class,
				EntityCopyNotAllowedObserver.SHORT_NAME,
				EntityCopyNotAllowedObserver.class
		);
		strategySelector.registerStrategyImplementor(
				EntityCopyObserver.class,
				EntityCopyAllowedObserver.SHORT_NAME,
				EntityCopyAllowedObserver.class
		);
		strategySelector.registerStrategyImplementor(
				EntityCopyObserver.class,
				EntityCopyAllowedLoggedObserver.SHORT_NAME,
				EntityCopyAllowedLoggedObserver.class
		);
	}
