	public static void applyTransactionTimeout(Query query, EntityManagerFactory emf) {
		EntityManagerHolder emHolder = (EntityManagerHolder) TransactionSynchronizationManager.getResource(emf);
		if (emHolder != null && emHolder.hasTimeout()) {
			int timeoutValue = (int) emHolder.getTimeToLiveInMillis();
			try {
				query.setHint("javax.persistence.query.timeout", timeoutValue);
			}
			catch (IllegalArgumentException ex) {
				// oh well, at least we tried...
			}
		}
	}
