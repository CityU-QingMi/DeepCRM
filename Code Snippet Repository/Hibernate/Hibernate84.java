	@Test
	public void test_criteria_typedquery_expression_example() {

        doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::criteria-typedquery-expression-example[]
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();

			CriteriaQuery<String> criteria = builder.createQuery( String.class );
			Root<Person> root = criteria.from( Person.class );
			criteria.select( root.get( Person_.nickName ) );
			criteria.where( builder.equal( root.get( Person_.name ), "John Doe" ) );

			List<String> nickNames = entityManager.createQuery( criteria ).getResultList();
			//end::criteria-typedquery-expression-example[]
			assertEquals(1, nickNames.size());
		});
	}
