	@Override
	public RuntimeException convert(RuntimeException e, LockOptions lockOptions) {
		RuntimeException result = e;
		if ( e instanceof HibernateException ) {
			result = convert( (HibernateException) e, lockOptions );
		}
		else {
			sharedSessionContract.markForRollbackOnly();
		}
		return result;
	}
