		@Override
		@Nullable
		public V call() throws Exception {
			try {
				return this.delegate.call();
			}
			catch (Throwable t) {
				this.errorHandler.handleError(t);
				return null;
			}
		}
