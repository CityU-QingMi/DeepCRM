	@Override
	public RuntimeException convert(RuntimeException e) {
		RuntimeException result = e;
		if ( e instanceof HibernateException ) {
			result = convert( (HibernateException) e );
		}
		else {
			sharedSessionContract.markForRollbackOnly();
		}
		return result;
	}
