	@Test
	public void test_criteria_from_fetch_example() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::criteria-from-fetch-example[]
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();

			CriteriaQuery<Phone> criteria = builder.createQuery( Phone.class );
			Root<Phone> root = criteria.from( Phone.class );

			// Phone.person is a @ManyToOne
			Fetch<Phone, Person> personFetch = root.fetch( Phone_.person );
			// Person.addresses is an @ElementCollection
			Fetch<Person, String> addressesJoin = personFetch.fetch( Person_.addresses );

			criteria.where( builder.isNotEmpty( root.get( Phone_.calls ) ) );

			List<Phone> phones = entityManager.createQuery( criteria ).getResultList();
			//end::criteria-from-fetch-example[]
			assertEquals(2, phones.size());
		});
	}
