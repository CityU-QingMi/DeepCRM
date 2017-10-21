	@Test
	public void test_criteria_from_multiple_root_example() {

        doInJPA( this::entityManagerFactory, entityManager -> {
			String address = "Earth";
			String prefix = "J%";
			//tag::criteria-from-multiple-root-example[]
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();

			CriteriaQuery<Tuple> criteria = builder.createQuery( Tuple.class );

			Root<Person> personRoot = criteria.from( Person.class );
			Root<Partner> partnerRoot = criteria.from( Partner.class );
			criteria.multiselect( personRoot, partnerRoot );

			Predicate personRestriction = builder.and(
				builder.equal( personRoot.get( Person_.address ), address ),
				builder.isNotEmpty( personRoot.get( Person_.phones ) )
			);
			Predicate partnerRestriction = builder.and(
				builder.like( partnerRoot.get( Partner_.name ), prefix ),
				builder.equal( partnerRoot.get( Partner_.version ), 0 )
			);
			criteria.where( builder.and( personRestriction, partnerRestriction ) );

			List<Tuple> tuples = entityManager.createQuery( criteria ).getResultList();
			//end::criteria-from-multiple-root-example[]
			assertEquals(2, tuples.size());
		});
	}
