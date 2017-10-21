	@Test
	public void test_criteria_from_join_example() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::criteria-from-join-example[]
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();

			CriteriaQuery<Phone> criteria = builder.createQuery( Phone.class );
			Root<Phone> root = criteria.from( Phone.class );

			// Phone.person is a @ManyToOne
			Join<Phone, Person> personJoin = root.join( Phone_.person );
			// Person.addresses is an @ElementCollection
			Join<Person, String> addressesJoin = personJoin.join( Person_.addresses );

			criteria.where( builder.isNotEmpty( root.get( Phone_.calls ) ) );

			List<Phone> phones = entityManager.createQuery( criteria ).getResultList();
			//end::criteria-from-join-example[]
			assertEquals(2, phones.size());
		});
	}
