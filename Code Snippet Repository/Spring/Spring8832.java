		@Override
		public void release() {
			if (this.transactionDelegate != null) {
				try {
					this.transactionDelegate.setRollbackOnly();
					this.transactionDelegate.endTransaction();
				}
				catch (Throwable ex) {
					logger.error("Could not complete unfinished transaction on endpoint release", ex);
				}
			}
		}
