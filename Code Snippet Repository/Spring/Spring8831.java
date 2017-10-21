		@Override
		public void afterDelivery() throws ResourceException {
			Assert.state(this.transactionDelegate != null, "Not initialized");
			this.beforeDeliveryCalled = false;
			Thread.currentThread().setContextClassLoader(this.previousContextClassLoader);
			this.previousContextClassLoader = null;
			try {
				this.transactionDelegate.endTransaction();
			}
			catch (Throwable ex) {
				throw new ApplicationServerInternalException("Failed to complete transaction", ex);
			}
		}
