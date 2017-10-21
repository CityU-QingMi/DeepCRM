	@Override
	public void afterCompletion(int status) {
		switch (status) {
			case Status.STATUS_COMMITTING:
			case Status.STATUS_COMMITTED:
				invokeIsolated(true);
				break;
			default:
				// it would be nicer to react only on ROLLING_BACK and ROLLED_BACK statuses
				// but TransactionCoordinator gives us UNKNOWN on rollback
				invokeIsolated(false);
				break;
		}
	}
