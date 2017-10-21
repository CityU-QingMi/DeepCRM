	@Override
	public <T> T delegateCallable(final Callable<T> callable, final boolean transacted) throws HibernateException {
		return doInSuspendedTransaction(new HibernateCallable<T>() {
			@Override
			public T call() throws HibernateException {
				HibernateCallable<T> workCallable = new HibernateCallable<T>() {
					@Override
					public T call() throws HibernateException {
						try {
							return callable.call();
						}
						catch (HibernateException e) {
							throw e;
						}
						catch (Exception e) {
							throw new HibernateException(e);
						}
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
