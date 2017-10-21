	@Override
	public <T> T delegateWork(final WorkExecutorVisitable<T> work, final boolean transacted) throws HibernateException {
		return doInSuspendedTransaction(new HibernateCallable<T>() {
			@Override
			public T call() throws HibernateException {
				HibernateCallable<T> workCallable = new HibernateCallable<T>() {
					@Override
					public T call() throws HibernateException {
						return doTheWork(work);
					}
				};
				if ( transacted ) {
					return doInNewTransaction( workCallable, transactionManager );
				}
				else {
					return workCallable.call();
				}
			}
		});
	}
