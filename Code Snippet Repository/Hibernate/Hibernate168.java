	@Test
	public void test_hql_implicit_join_alias_example_1() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			String address = "Earth";
			Date timestamp = new Date(0);
			//tag::hql-implicit-join-alias-example[]
			List<Phone> phones = entityManager.createQuery(
				"select ph " +
				"from Phone ph " +
				"where ph.person.address = :address " +
				"  and ph.person.createdOn > :timestamp", Phone.class )
			.setParameter( "address", address )
			.setParameter( "timestamp", timestamp )
			.getResultList();
			//end::hql-implicit-join-alias-example[]
			assertEquals(3, phones.size());
		});
	}
