	private void internalCleanup() throws Exception {
		withTxSession(useJta, sessionFactory, session -> {
			Query query = session.createQuery("select account from Account as account");
			List accts = query.list();
			if (accts != null) {
				for (Iterator it = accts.iterator(); it.hasNext(); ) {
					try {
						Object acct = it.next();
						log.info("Removing " + acct);
						session.delete(acct);
					} catch (Exception ignored) {
					}
				}
			}
		});
	}
