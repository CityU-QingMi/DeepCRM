	@Test
	public void test_sql_hibernate_multiple_scalar_values_dto_hibernate_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-multiple-scalar-values-dto-hibernate-named-query-example[]
			List<PersonPhoneCount> personNames = session.getNamedNativeQuery(
				"get_person_phone_count")
			.getResultList();
			//end::sql-hibernate-multiple-scalar-values-dto-hibernate-named-query-example[]
			assertEquals(2, personNames.size());
			assertEquals(1, personNames.stream().filter( person -> person.getName().equals( "John Doe" ) ).map( PersonPhoneCount::getPhoneCount ).findAny().get().intValue());
			assertEquals(2, personNames.stream().filter( person -> person.getName().equals( "Mrs. John Doe" ) ).map( PersonPhoneCount::getPhoneCount ).findAny().get().intValue());
		});
	}
