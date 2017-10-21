	@Test
	public void test_hql_implicit_join_alias_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			String address = "Earth";
			Date timestamp = new Date(0);
			//tag::hql-implicit-join-alias-example[]

			//same as
			List<Phone> phones = entityManager.createQuery(
				"select ph " +
				"from Phone ph " +
				"inner join ph.person pr " +
				"where pr.address = :address " +
				"  and pr.createdOn > :timestamp", Phone.class )
			.setParameter( "address", address )
			.setParameter( "timestamp", timestamp )
			.getResultList();
			//end::hql-implicit-join-alias-example[]
			assertEquals(3, phones.size());
		});
	}
