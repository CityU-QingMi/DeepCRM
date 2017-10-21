	protected void endTransaction() {
		final boolean commit = this.complete;
		if (this.transactionStatus != null) {
			try {
				if (commit) {
					this.transactionManager.commit(this.transactionStatus);
				}
				else {
					this.transactionManager.rollback(this.transactionStatus);
				}
			}
			finally {
				this.transactionStatus = null;
			}
		}
	}
