	@Test
	public void test_hql_in_predicate_example_3() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-in-predicate-example[]

			List<Phone> phones = entityManager.createQuery(
				"select p " +
				"from Phone p " +
				"where type in :types", Phone.class )
			.setParameter( "types", Arrays.asList( PhoneType.MOBILE, PhoneType.LAND_LINE ) )
			.getResultList();
			//end::hql-in-predicate-example[]
			assertEquals(3, phones.size());
		});
	}
