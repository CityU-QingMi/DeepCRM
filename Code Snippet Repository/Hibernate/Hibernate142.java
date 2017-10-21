	@Test
	public void test_hql_explicit_join_with_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::hql-explicit-join-with-example[]
			List<Object[]> personsAndPhones = session.createQuery(
				"select pr.name, ph.number " +
				"from Person pr " +
				"left join pr.phones ph with ph.type = :phoneType " )
			.setParameter( "phoneType", PhoneType.LAND_LINE )
			.list();
			//end::hql-explicit-join-with-example[]
			assertEquals(4, personsAndPhones.size());
		});
	}
