	@Test
	public void test_hql_implicit_join_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			String address = "Earth";
			//tag::hql-implicit-join-example[]

			// same as
			List<Phone> phones = entityManager.createQuery(
				"select ph " +
				"from Phone ph " +
				"join ph.person pr " +
				"where pr.address = :address ", Phone.class )
			.setParameter( "address", address)
			.getResultList();
			//end::hql-implicit-join-example[]
			assertEquals(3, phones.size());
		});
	}
