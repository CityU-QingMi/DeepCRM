	@Test
	@TestForIssue( jiraKey = "")
	public void testGetIdManyToOne() {
		Serializable accountId = doInHibernate(this::sessionFactory, s -> {
			Address address = new Address();
			s.save(address);
			Client client = new Client(address);
			Account account = new Account();
			client.addAccount(account);
			s.save(account);
			s.save(client);
			return account.getId();
		});

		doInHibernate(this::sessionFactory, s -> {
			Account account = s.load(Account.class, accountId);
			Client client = account.getClient();
			client.getId();
			assertThat(Hibernate.isInitialized(client), is(false));
			client.getName();
			assertThat(Hibernate.isInitialized(client), is(true));
		});

	}
