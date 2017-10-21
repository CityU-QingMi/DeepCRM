		@Override
		public void beforeDelivery(@Nullable Method method) throws ResourceException {
			this.beforeDeliveryCalled = true;
			Assert.state(this.transactionDelegate != null, "Not initialized");
			try {
				this.transactionDelegate.beginTransaction();
			}
			catch (Throwable ex) {
				throw new ApplicationServerInternalException("Failed to begin transaction", ex);
			}
			Thread currentThread = Thread.currentThread();
			this.previousContextClassLoader = currentThread.getContextClassLoader();
			currentThread.setContextClassLoader(getEndpointClassLoader());
		}
