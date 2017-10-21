	@Test
	public void test_criteria_param_example() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::criteria-param-example[]
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();

			CriteriaQuery<Person> criteria = builder.createQuery( Person.class );
			Root<Person> root = criteria.from( Person.class );

			ParameterExpression<String> nickNameParameter = builder.parameter( String.class );
			criteria.where( builder.equal( root.get( Person_.nickName ), nickNameParameter ) );

			TypedQuery<Person> query = entityManager.createQuery( criteria );
			query.setParameter( nickNameParameter, "JD" );
			List<Person> persons = query.getResultList();
			//end::criteria-param-example[]
			assertEquals(1, persons.size());
		});
	}
