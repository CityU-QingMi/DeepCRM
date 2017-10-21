	@Test
	public void test_jpql_explicit_join_on_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-explicit-join-jpql-on-example[]
			List<Object[]> personsAndPhones = entityManager.createQuery(
				"select pr.name, ph.number " +
				"from Person pr " +
				"left join pr.phones ph on ph.type = :phoneType " )
			.setParameter( "phoneType", PhoneType.LAND_LINE )
			.getResultList();
			//end::hql-explicit-join-jpql-on-example[]
			assertEquals(4, personsAndPhones.size());
		});
	}
