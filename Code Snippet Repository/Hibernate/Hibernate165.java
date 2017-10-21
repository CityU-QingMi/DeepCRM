	@Test
	public void test_hql_read_only_entities_example() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-read-only-entities-example[]
			List<Call> calls = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"join c.phone p " +
				"where p.number = :phoneNumber ", Call.class )
			.setParameter( "phoneNumber", "123-456-7890" )
			.setHint( "org.hibernate.readOnly", true )
			.getResultList();

			calls.forEach( c -> c.setDuration( 0 ) );
			//end::hql-read-only-entities-example[]
		});
	}
