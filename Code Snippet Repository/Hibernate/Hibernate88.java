	@Test
	public void test_criteria_tuple_example() {

        doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::criteria-tuple-example[]
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();

			CriteriaQuery<Tuple> criteria = builder.createQuery( Tuple.class );
			Root<Person> root = criteria.from( Person.class );

			Path<Long> idPath = root.get( Person_.id );
			Path<String> nickNamePath = root.get( Person_.nickName);

			criteria.multiselect( idPath, nickNamePath );
			criteria.where( builder.equal( root.get( Person_.name ), "John Doe" ) );

			List<Tuple> tuples = entityManager.createQuery( criteria ).getResultList();

			for ( Tuple tuple : tuples ) {
				Long id = tuple.get( idPath );
				String nickName = tuple.get( nickNamePath );
			}

			//or using indices
			for ( Tuple tuple : tuples ) {
				Long id = (Long) tuple.get( 0 );
				String nickName = (String) tuple.get( 1 );
			}
			//end::criteria-tuple-example[]
			assertEquals(1, tuples.size());
		});
	}
