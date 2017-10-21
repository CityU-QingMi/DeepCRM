	@Override
	public boolean execute() {
		try {
			final Output rtn = outputs().getCurrent();
			return rtn != null && ResultSetOutput.class.isInstance( rtn );
		}
		catch (NoMoreReturnsException e) {
			return false;
		}
		catch (HibernateException he) {
			throw getExceptionConverter().convert( he );
		}
		catch (RuntimeException e) {
			getProducer().markForRollbackOnly();
			throw e;
		}
	}
