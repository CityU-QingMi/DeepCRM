	@Test
	public void test_criteria_group_by_example() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::criteria-group-by-example[]
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();

			CriteriaQuery<Tuple> criteria = builder.createQuery( Tuple.class );
			Root<Person> root = criteria.from( Person.class );

			criteria.groupBy(root.get("address"));
			criteria.multiselect(root.get("address"), builder.count(root));

			List<Tuple> tuples = entityManager.createQuery( criteria ).getResultList();

			for ( Tuple tuple : tuples ) {
				String name = (String) tuple.get( 0 );
				Long count = (Long) tuple.get( 1 );
			}
			//end::criteria-group-by-example[]
			assertEquals(2, tuples.size());
		});
	}
