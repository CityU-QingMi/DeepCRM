	@Override
	protected boolean shouldCloseJdbcCoordinatorOnClose(boolean isTransactionCoordinatorShared) {
		if ( !isTransactionCoordinatorShared ) {
			return super.shouldCloseJdbcCoordinatorOnClose( isTransactionCoordinatorShared );
		}

		if ( getActionQueue().hasBeforeTransactionActions() || getActionQueue().hasAfterTransactionActions() ) {
			log.warn(
					"On close, shared Session had before/after transaction actions that have not yet been processed"
			);
		}
		return false;
	}
