	public static void markRollbackOnly(boolean useJta, Session s) {
		if (useJta) {
			JtaPlatform jtaPlatform = s.getSessionFactory().getSessionFactoryOptions().getServiceRegistry().getService(JtaPlatform.class);
			TransactionManager tm = jtaPlatform.retrieveTransactionManager();
			try {
				tm.setRollbackOnly();
			} catch (SystemException e) {
				throw new RuntimeException(e);
			}
		} else {
			s.getTransaction().markRollbackOnly();
		}
	}
