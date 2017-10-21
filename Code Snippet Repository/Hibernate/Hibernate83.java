	@Test
	public void test_criteria_typedquery_entity_example() {

        doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::criteria-typedquery-entity-example[]
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();

			CriteriaQuery<Person> criteria = builder.createQuery( Person.class );
			Root<Person> root = criteria.from( Person.class );
			criteria.select( root );
			criteria.where( builder.equal( root.get( Person_.name ), "John Doe" ) );

			List<Person> persons = entityManager.createQuery( criteria ).getResultList();
			//end::criteria-typedquery-entity-example[]
			assertEquals(1, persons.size());
		});
	}
