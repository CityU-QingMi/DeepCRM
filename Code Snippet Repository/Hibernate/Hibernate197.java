	@Test
	public void test_hql_api_stream_projection_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::hql-api-stream-projection-example[]
			try ( Stream<Object[]> persons = session.createQuery(
				"select p.name, p.nickName " +
				"from Person p " +
				"where p.name like :name" )
			.setParameter( "name", "J%" )
			.stream() ) {

				persons
				.map( row -> new PersonNames(
						(String) row[0],
						(String) row[1] ) )
				.forEach( this::process );
			}
			//end::hql-api-stream-projection-example[]
		});
	}
