	public void updateAccountBranch(Integer id, String branch) throws Exception {
		withTxSession(useJta, sessionFactory, session -> {
			log.debug("Updating account " + id + " to branch " + branch);
			Account account = session.get(Account.class, id);
			log.debug("Set branch " + branch);
			account.setBranch(branch);
			session.update(account);
			log.debug("Updated account " + id + " to branch " + branch);
		});
	}
