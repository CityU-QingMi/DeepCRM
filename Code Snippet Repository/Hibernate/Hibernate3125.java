	@Override
	public <T> T doReturningWork(final ReturningWork<T> work) throws HibernateException {
		WorkExecutorVisitable<T> realWork = new WorkExecutorVisitable<T>() {
			@Override
			public T accept(WorkExecutor<T> workExecutor, Connection connection) throws SQLException {
				return workExecutor.executeReturningWork( work, connection );
			}
		};
		return doWork( realWork );
	}
