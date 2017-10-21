	@Test
	public void testLegacyCriteriaJpavsHibernateEntityName() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			Event event1 = new Event();
			event1.id = 1L;
			event1.name = "E1";
			entityManager.persist( event1 );

			Event event2 = new Event();
			event2.id = 2L;
			event2.name = "E2";
			entityManager.persist( event2 );

			List<String> eventNames = entityManager.unwrap( Session.class )
					.createQuery( "select ae.name from ApplicationEvent ae" )
					.list();

			try {
				List<Event> events = entityManager.unwrap( Session.class )
				.createCriteria( "ApplicationEvent" )
				.list();
			}
			catch ( MappingException expected ) {
				assertEquals( "Unknown entity: ApplicationEvent", expected.getMessage() );
			}

			List<Event> events = entityManager.unwrap( Session.class )
			.createCriteria( Event.class.getName() )
			.list();

		});
	}
