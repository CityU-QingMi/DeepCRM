	@Test
	public void test_criteria_from_root_example() {

        doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::criteria-from-root-example[]
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();

			CriteriaQuery<Person> criteria = builder.createQuery( Person.class );
			Root<Person> root = criteria.from( Person.class );
			//end::criteria-from-root-example[]
		});
	}
