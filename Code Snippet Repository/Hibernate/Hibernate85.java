	@Test
	public void test_criteria_typedquery_multiselect_explicit_array_example() {

        doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::criteria-typedquery-multiselect-array-explicit-example[]
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();

			CriteriaQuery<Object[]> criteria = builder.createQuery( Object[].class );
			Root<Person> root = criteria.from( Person.class );

			Path<Long> idPath = root.get( Person_.id );
			Path<String> nickNamePath = root.get( Person_.nickName);

			criteria.select( builder.array( idPath, nickNamePath ) );
			criteria.where( builder.equal( root.get( Person_.name ), "John Doe" ) );

			List<Object[]> idAndNickNames = entityManager.createQuery( criteria ).getResultList();
			//end::criteria-typedquery-multiselect-array-explicit-example[]
			assertEquals(1, idAndNickNames.size());
		});
	}
