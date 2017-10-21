	@Override
	public <T> T delegateCallable(Callable<T> callable, boolean transacted) throws HibernateException {
		// No connection, nothing to be suspended
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
