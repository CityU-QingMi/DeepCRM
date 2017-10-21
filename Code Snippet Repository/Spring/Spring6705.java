		@Override
		public void onException(JMSException ex) {
			// Iterate over temporary copy in order to avoid ConcurrentModificationException,
			// since listener invocations may in turn trigger registration of listeners...
			Set<ExceptionListener> copy;
			synchronized (connectionMonitor) {
				copy = new LinkedHashSet<>(this.delegates);
			}
			for (ExceptionListener listener : copy) {
				listener.onException(ex);
			}
		}
