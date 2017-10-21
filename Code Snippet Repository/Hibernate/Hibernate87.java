	@Test
	public void test_criteria_typedquery_wrapper_example() {

        doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::criteria-typedquery-wrapper-example[]

			CriteriaBuilder builder = entityManager.getCriteriaBuilder();

			CriteriaQuery<PersonWrapper> criteria = builder.createQuery( PersonWrapper.class );
			Root<Person> root = criteria.from( Person.class );

			Path<Long> idPath = root.get( Person_.id );
			Path<String> nickNamePath = root.get( Person_.nickName);

			criteria.select( builder.construct( PersonWrapper.class, idPath, nickNamePath ) );
			criteria.where( builder.equal( root.get( Person_.name ), "John Doe" ) );

			List<PersonWrapper> wrappers = entityManager.createQuery( criteria ).getResultList();
			//end::criteria-typedquery-wrapper-example[]
			assertEquals(1, wrappers.size());
		});
	}
