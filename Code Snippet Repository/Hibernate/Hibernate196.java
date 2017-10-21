	@Test
	public void test_hql_api_stream_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::hql-api-stream-example[]
			try( Stream<Person> persons = session.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name" )
			.setParameter( "name", "J%" )
			.stream() ) {

				Map<Phone, List<Call>> callRegistry = persons
						.flatMap( person -> person.getPhones().stream() )
						.flatMap( phone -> phone.getCalls().stream() )
						.collect( Collectors.groupingBy( Call::getPhone ) );

				process(callRegistry);
			}
			//end::hql-api-stream-example[]
		});
	}
