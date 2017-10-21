	@Test
	public void test_hql_read_only_entities_native_example() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-read-only-entities-native-example[]
			List<Call> calls = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"join c.phone p " +
				"where p.number = :phoneNumber ", Call.class )
			.setParameter( "phoneNumber", "123-456-7890" )
			.unwrap( org.hibernate.query.Query.class )
			.setReadOnly( true )
			.getResultList();
			//end::hql-read-only-entities-native-example[]
		});
	}
