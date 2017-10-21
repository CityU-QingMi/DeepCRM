	@Override
	public void doWork(final Work work) throws HibernateException {
		WorkExecutorVisitable<Void> realWork = new WorkExecutorVisitable<Void>() {
			@Override
			public Void accept(WorkExecutor<Void> workExecutor, Connection connection) throws SQLException {
				workExecutor.executeWork( work, connection );
				return null;
			}
		};
		doWork( realWork );
	}
