	public String getBranch(Object holder, boolean useRegion) throws Exception {
		return withTxSessionApply(useJta, sessionFactory, session -> {
			Query query = session.createQuery(
					"select account.branch from Account as account where account.accountHolder = ?");
			query.setParameter(0, holder);
			if (useRegion) {
				query.setCacheRegion("AccountRegion");
			}
			query.setCacheable(true);
			return (String) query.list().get(0);
		});
	}
