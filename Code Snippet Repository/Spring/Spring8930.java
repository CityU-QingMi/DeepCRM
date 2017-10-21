		@Override
		public void run() {
			UOWManager uowManager = obtainUOWManager();
			DefaultTransactionStatus status = prepareTransactionStatus(
					this.definition, (this.actualTransaction ? this : null),
					this.newTransaction, this.newSynchronization, this.debug, null);
			try {
				this.result = this.callback.doInTransaction(status);
				triggerBeforeCommit(status);
			}
			catch (Throwable ex) {
				this.exception = ex;
				uowManager.setRollbackOnly();
			}
			finally {
				if (status.isLocalRollbackOnly()) {
					if (status.isDebug()) {
						logger.debug("Transactional code has requested rollback");
					}
					uowManager.setRollbackOnly();
				}
				triggerBeforeCompletion(status);
				if (status.isNewSynchronization()) {
					List<TransactionSynchronization> synchronizations = TransactionSynchronizationManager.getSynchronizations();
					TransactionSynchronizationManager.clear();
					if (!synchronizations.isEmpty()) {
						uowManager.registerInterposedSynchronization(new JtaAfterCompletionSynchronization(synchronizations));
					}
				}
			}
		}
