	public int getCountForBranch(String branch, boolean useRegion) throws Exception {
		return withTxSessionApply(useJta, sessionFactory, session -> {
			Query query = session.createQuery(
					"select account from Account as account where account.branch = :branch");
			query.setString("branch", branch);
			if (useRegion) {
				query.setCacheRegion("AccountRegion");
			}
			query.setCacheable(true);
			return query.list().size();
		});
	}
