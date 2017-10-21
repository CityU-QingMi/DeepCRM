	@Test
	@TestForIssue( jiraKey = "")
	public void testGetIdOneToOne() {
		Serializable clientId = doInHibernate(this::sessionFactory, s -> {
			Address address = new Address();
			s.save(address);
			Client client = new Client(address);
			return s.save(client);
		});

		Serializable addressId = doInHibernate(this::sessionFactory, s -> {
			Client client = s.get(Client.class, clientId);
			Address address = client.getAddress();
			address.getId();
			assertThat(Hibernate.isInitialized(address), is(true));
			address.getStreet();
			assertThat(Hibernate.isInitialized(address), is(true));
			return address.getId();
		});

		doInHibernate(this::sessionFactory, s -> {
			Address address = s.get(Address.class, addressId);
			Client client = address.getClient();
			client.getId();
			assertThat(Hibernate.isInitialized(client), is(false));
			client.getName();
			assertThat(Hibernate.isInitialized(client), is(true));
		});
	}
