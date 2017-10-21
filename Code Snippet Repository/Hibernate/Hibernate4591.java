		@Override
		public void markRollbackOnly() {
			if ( getStatus() != TransactionStatus.ROLLED_BACK && getStatus() != TransactionStatus.NOT_ACTIVE ) {
				if ( log.isDebugEnabled() ) {
					log.debug(
							"JDBC transaction marked for rollback-only (exception provided for stack trace)",
							new Exception( "exception just for purpose of providing stack trace" )
					);
				}

				rollbackOnly = true;
			}
		}
