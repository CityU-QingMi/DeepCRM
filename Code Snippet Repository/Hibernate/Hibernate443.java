	@Test
	public void test_sql_hibernate_entity_associations_query_one_to_many_join_example_1() {
		try {
			doInJPA( this::entityManagerFactory, entityManager -> {
				Session session = entityManager.unwrap( Session.class );
				List<Phone> phones = session.createNativeQuery(
					"SELECT * " +
					"FROM Phone ph " +
					"JOIN phone_call c ON c.phone_id = ph.id" )
				.addEntity("phone", Phone.class )
				.addJoin( "c", "phone.calls")
				.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY )
				.list();

				for(Phone phone : phones) {
					List<Call> calls = phone.getCalls();
				}
				assertEquals(2, phones.size());
			});
		}
		catch (Exception e) {
			log.error( "HHH-10504", e );
			//See issue https://hibernate.atlassian.net/browse/HHH-10504
		}
	}
