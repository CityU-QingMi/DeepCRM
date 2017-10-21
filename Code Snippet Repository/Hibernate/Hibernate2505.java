	private void checkAgainstExtendedContract(T listener) {
		if ( !extendedListenerContract.isInstance( listener ) ) {
			log.warnf(
					"Encountered event listener [%s] for post-commit event [%s] "
							+ "which did not implement the corresponding extended "
							+ "listener contract [%s]",
					listener.getClass().getName(),
					getEventType().eventName(),
					extendedListenerContract.getName()
			);
		}
	}
