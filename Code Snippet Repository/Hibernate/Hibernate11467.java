	public static <T> T withinTx(
			TransactionManager tm,
			Callable<T> c) throws Exception {
		if ( tm == null ) {
			try {
				return c.call();
			}
			catch (Exception e) {
				throw e;
			}
		}
		else {
			tm.begin();
			try {
				return c.call();
			}
			catch (Exception e) {
				tm.setRollbackOnly();
				throw e;
			}
			finally {
				if ( tm.getStatus() == Status.STATUS_ACTIVE ) {
					tm.commit();
				}
				else {
					tm.rollback();
				}
			}
		}
	}
