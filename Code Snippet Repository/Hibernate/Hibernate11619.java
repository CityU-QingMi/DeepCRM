	public int getTotalBalance(AccountHolder holder, boolean useRegion) throws Exception {
		List results = (List) withTxSessionApply(useJta, sessionFactory, session -> {
			Query query = session.createQuery(
					"select account.balance from Account as account where account.accountHolder = ?");
			query.setParameter(0, holder);
			if (useRegion) {
				query.setCacheRegion("AccountRegion");
			}
			query.setCacheable(true);
			return query.list();
		});
		int total = 0;
		if (results != null) {
			for (Iterator it = results.iterator(); it.hasNext();) {
				total += ((Integer) it.next()).intValue();
				System.out.println("Total = " + total);
			}
		}
		return total;
	}
