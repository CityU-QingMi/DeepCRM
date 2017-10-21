	protected void invokeIsolated(final boolean success) {
		try {
			// TODO: isolation without obtaining Connection -> needs HHH-9993
			tc.createIsolationDelegate().delegateWork(new WorkExecutorVisitable<Void>() {
				@Override
				public Void accept(WorkExecutor<Void> executor, Connection connection) throws SQLException {
					invoke(success);
					return null;
				}
			}, requiresTransaction);
		}
		catch (HibernateException e) {
			// silently fail any exceptions
			if (log.isTraceEnabled()) {
				log.trace("Exception during query cache update", e);
			}
		}
	}
